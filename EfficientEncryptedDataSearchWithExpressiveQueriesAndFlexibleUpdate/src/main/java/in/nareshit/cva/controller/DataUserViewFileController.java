package in.nareshit.cva.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.RequestStatus;
import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.service.impl.RequestStatusServiceImpl;
import in.nareshit.cva.service.impl.UploadFileServiceImpl;


@WebServlet("/viewFile")
public class DataUserViewFileController extends HttpServlet{


	private static Logger logger = LoggerFactory.getLogger(DataUserViewFileController.class);


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataUserViewFileController::doGet(req,resp)::started <<==================");

		/* due to UI Bar Progress */
		try {
			new Thread();
			Thread.sleep(1000*4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//UploadFile uploadFile = new UploadFileServiceImpl().getUploadedFileById(Integer.parseInt(req.getParameter("fileId")));
		UploadFile uploadFile =null;

		String role=(String) req.getSession().getAttribute("role");
		String loginId=(String) req.getSession().getAttribute("loginId");
		Integer fileId = Integer.parseInt(req.getParameter("fileId"));
		String enteredfileKey=req.getParameter("fileKey");
		String oldKey = req.getParameter("oldKey");

		logger.info("-----------------------> role     :: "+role+" <-----------------------");
		logger.info("-----------------------> userId :: "+loginId+" <-----------------------");
		logger.info("-----------------------> filedId  :: "+fileId+" <-----------------------");
		logger.info("-----------------------> oldKey  :: "+oldKey+" <-----------------------");
		logger.info("-----------------------> enteredfileKey  :: "+enteredfileKey+" <-----------------------");

		if(enteredfileKey.equals(oldKey)) {
			if(role!=null && (role.equals("DataUser") || role.equals("DataOwner"))) {
				/* Download/viewing purpose */
				uploadFile = new UploadFileServiceImpl().getUploadedFileById(new UploadFile(fileId));

				logger.info("-----------------------> uploadFile:: "+uploadFile+" <-----------------------");

			/*	System.out.println("---------------------FileEncryption::Start----------------------");
				try {
					UploadFile uploadedFileWithDecrypt =  FileEncryption.decrypt(uploadFile.getFileKey(), uploadFile.getFile());
					uploadFile.setFile(uploadedFileWithDecrypt.getFile());
					uploadFile.setFileKey(uploadedFileWithDecrypt.getFileKey());

				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("---------------------FileEncryption::End----------------------");

			*/
				resp.setContentType("text/html");
				//-- View

				if(uploadFile.getFileName().toLowerCase().contains(".pdf"))
					resp.setContentType("application/pdf");
				else if(uploadFile.getFileName().toLowerCase().contains(".docx"))
					resp.setContentType("application/msword");
				else if(uploadFile.getFileName().toLowerCase().contains(".doc"))
					resp.setContentType("application/msword");
				else if(uploadFile.getFileName().toLowerCase().contains(".csv"))
					resp.setContentType("text/csv");
				else if(uploadFile.getFileName().toLowerCase().contains(".jpg"))
					resp.setContentType("image/jpeg");
				else if(uploadFile.getFileName().toLowerCase().contains(".jpeg"))
					resp.setContentType("image/jpeg");
				else if(uploadFile.getFileName().toLowerCase().contains(".png"))
					resp.setContentType("image/jpeg");
				else if(uploadFile.getFileName().toLowerCase().contains(".mp4"))
					resp.setContentType("video/mp4");
				else if(uploadFile.getFileName().toLowerCase().contains(".mp3"))
					resp.setContentType("audio/mpeg");

				System.out.println("getContentType()::"+resp.getContentType());
				resp.setHeader("Content-Disposition", "inline; filename=\""+ uploadFile.getFileName() + "\"");

				OutputStream os = resp.getOutputStream();
				int i;
				try {
					//byte[] buffer = new byte[1024*4];
					while ((i = uploadFile.getFile().read()) != -1) {
						os.write(i);
					}
					os.flush();
					os.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}else {
				req.setAttribute("msg", "Please Enter UserId & Password !!");
				req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);
			}
		}else {
			sendRequest(fileId,enteredfileKey, loginId);
			req.setAttribute("msg", "Key is invalid !! <br>key is might updated by DataOwner. <br>You will get the file key once Admin Accepted !!");
			req.getRequestDispatcher("./jsps/DataUserSearch.jsp").forward(req, resp);
		}
		logger.info("==================>> DataUserViewFileController::doGet(req,resp)::Ended <<==================");
	}

	protected Boolean sendRequest(Integer fileId, String enteredfileKey, String loginId) {
		logger.info("==================>> sendRequest::started <<==================");
		
		Boolean flag=false;

		List<RequestStatus> allRequestsByAdmin = new RequestStatusServiceImpl().getAllRequestsByAdmin();
		for (RequestStatus requestStatus : allRequestsByAdmin) {
			if (requestStatus.getFid()==fileId && requestStatus.getLoginId().equals(loginId)) {
				return flag;
			}
		}
		
		UploadFile uploadFile = new UploadFile(fileId, enteredfileKey, loginId);
		System.out.println("<-------------------check---------------------->");
		if (new RequestStatusServiceImpl().sendRequest(uploadFile, loginId)>0) 
			flag= true;
		else flag= false;

		logger.info("==================>> sendRequest()::Ended <<==================");

		return flag;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UploadFile uploadFile =null;

		String role=(String) req.getSession().getAttribute("role");
		String loginId=(String) req.getSession().getAttribute("loginId");
		Integer fileId = Integer.parseInt(req.getParameter("fileId"));

		logger.info("-----------------------> role     :: "+role+" <-----------------------");
		logger.info("-----------------------> userId :: "+loginId+" <-----------------------");
		logger.info("-----------------------> filedId  :: "+fileId+" <-----------------------");

			if(role!=null &&  role.equals("DataOwner")) {
				/* Download/viewing purpose */
				uploadFile = new UploadFileServiceImpl().getUploadedFileById(new UploadFile(fileId));

				logger.info("-----------------------> uploadFile:: "+uploadFile+" <-----------------------");

				resp.setContentType("text/html");
				//-- View

				if(uploadFile.getFileName().toLowerCase().contains(".pdf"))
					resp.setContentType("application/pdf");
				else if(uploadFile.getFileName().toLowerCase().contains(".docx"))
					resp.setContentType("application/msword");
				else if(uploadFile.getFileName().toLowerCase().contains(".doc"))
					resp.setContentType("application/msword");
				else if(uploadFile.getFileName().toLowerCase().contains(".csv"))
					resp.setContentType("text/csv");
				else if(uploadFile.getFileName().toLowerCase().contains(".jpg"))
					resp.setContentType("image/jpeg");
				else if(uploadFile.getFileName().toLowerCase().contains(".jpeg"))
					resp.setContentType("image/jpeg");
				else if(uploadFile.getFileName().toLowerCase().contains(".png"))
					resp.setContentType("image/jpeg");
				else if(uploadFile.getFileName().toLowerCase().contains(".mp4"))
					resp.setContentType("video/mp4");
				else if(uploadFile.getFileName().toLowerCase().contains(".mp3"))
					resp.setContentType("audio/mpeg");

				System.out.println("getContentType()::"+resp.getContentType());
				resp.setHeader("Content-Disposition", "inline; filename=\""+ uploadFile.getFileName() + "\"");

				OutputStream os = resp.getOutputStream();
				int i;
				try {
					//byte[] buffer = new byte[1024*4];
					while ((i = uploadFile.getFile().read()) != -1) {
						os.write(i);
					}
					os.flush();
					os.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}else {
				req.setAttribute("msg", "Please Enter UserId & Password !!");
				req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);
			}
		logger.info("==================>> DataUserViewFileController::doGet(req,resp)::Ended <<==================");
	}
}
