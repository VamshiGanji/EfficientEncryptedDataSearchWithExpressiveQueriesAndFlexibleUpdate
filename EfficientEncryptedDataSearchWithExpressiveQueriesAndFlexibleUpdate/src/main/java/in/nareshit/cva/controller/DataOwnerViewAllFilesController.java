package in.nareshit.cva.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.service.impl.UploadFileServiceImpl;


@WebServlet("/viewAllFiles")
//@ServletSecurity(@HttpConstraint(rolesAllowed = "DataUser"))
public class DataOwnerViewAllFilesController extends HttpServlet{


	private static Logger logger = LoggerFactory.getLogger(DataOwnerViewAllFilesController.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataOwnerViewAllFilesController::doGet(req,resp)::started <<==================");
		List<UploadFile> uploadFiles = new UploadFileServiceImpl().getStorageNodeAllFiles();

		//uploadFiles.forEach(System.out::println);

		logger.info("-----------------------> List<UploadFile>:: "+uploadFiles+" <-----------------------");
		if (uploadFiles==null || uploadFiles.isEmpty()) 
			req.setAttribute("msg", "No Records found !!");
		else if(!uploadFiles.isEmpty()) {
			req.setAttribute("uploadFiles", uploadFiles);
		}else {
			req.setAttribute("uploadFiles", uploadFiles);
		}

		req.getRequestDispatcher("./jsps/DataOwnerViewAllFiles.jsp").forward(req, resp);
		logger.info("==================>> DataOwnerViewAllFilesController::doGet(req,resp)::Ended <<==================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataOwnerViewAllFilesController::doPost(req,resp)::started <<==================");

		System.out.println("---------------------req::fileId::"+req.getParameter("fileId")+"----------------------");
		System.out.println("---------------------req::oldKey::"+req.getParameter("oldKey")+"----------------------");
		System.out.println("---------------------req::newFileKey::"+req.getParameter("fileKey")+"----------------------");
		
		Integer fileId = Integer.parseInt(req.getParameter("fileId"));
		String fileKey=req.getParameter("fileKey");
		
		
		UploadFile uploadedFileById = new UploadFileServiceImpl().getUploadedFileById(new UploadFile(fileId));
		uploadedFileById.setLoginId((String)req.getSession().getAttribute("loginId"));
		/* due to UI Bar Progress */
		try {
			new Thread();
			Thread.sleep(1000*7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

/*		System.out.println("---------------------FileDecryption::Start----------------------");
		try {
			UploadFile uploadedFileWithDecrypt =  FileEncryption.decrypt(uploadedFileById.getFileKey(), uploadedFileById.getFile());
			uploadedFileById.setFile(uploadedFileWithDecrypt.getFile());
			uploadedFileById.setFileKey(uploadedFileWithDecrypt.getFileKey());

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---------------------FileDecryption::End----------------------");
*/
		// new key
		uploadedFileById.setFileKey(fileKey);

		Integer updateStatus = new UploadFileServiceImpl().updateFileKey(uploadedFileById);

		//uploadFiles.forEach(System.out::println);

		logger.info("-----------------------> New Key :: "+uploadedFileById.getFileKey()+" <-----------------------");

		if (updateStatus>0) 
			req.setAttribute("msg", "Key updated successfully !!");
		else 
			req.setAttribute("msg", "Key updated failed !!");

		List<UploadFile> uploadFiles = new UploadFileServiceImpl().getStorageNodeAllFiles();
		req.setAttribute("uploadFiles", uploadFiles);
		logger.info("-----------------------> List<UploadFile>:: "+uploadFiles+" <-----------------------");

		req.getRequestDispatcher("./jsps/DataOwnerViewAllFiles.jsp").forward(req, resp);

		logger.info("==================>> DataOwnerViewAllFilesController::doPost(req,resp)::Ended <<==================");
	}
}
