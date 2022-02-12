package in.nareshit.cva.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/downloadFile")
public class DataUserDownloadFileController extends HttpServlet{


	private static Logger logger = LoggerFactory.getLogger(DataUserDownloadFileController.class);


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataUserViewFileController::doGet(req,resp)::started <<==================");

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

		resp.setContentType("text/html");

		if(enteredfileKey.equals(oldKey)) {
			if(role!=null && (role.equals("DataUser") || role.equals("DataOwner"))) {
				/* Download/viewing purpose */
				uploadFile = new UploadFileServiceImpl().getUploadedFileById(new UploadFile(Integer.parseInt(req.getParameter("fileId"))));

				logger.info("-----------------------> uploadFile:: "+uploadFile+" <-----------------------");

				//-- Download
				resp.setContentType("APPLICATION/OCTET-STREAM");
				resp.setHeader("Content-Disposition", "attachment; filename=\""+ uploadFile.getFileName() + "\"");
				PrintWriter out = resp.getWriter();
				int i;
				try {
					while ((i = uploadFile.getFile().read()) != -1) {
						out.write(i);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				//req.getRequestDispatcher("./jsps/DataUserSearch.jsp").forward(req, resp);
				logger.info("==================>> DataUserDownloadFileController::doGet(req,resp)::Ended <<==================");
			}
		}else {
			sendRequest(fileId,enteredfileKey, loginId);
			req.setAttribute("msg", "Key is invalid !! <br>key is might updated by DataOwner. <br>You will get the file key once Admin Accepted !!");
			req.getRequestDispatcher("./jsps/DataUserSearch.jsp").forward(req, resp);
		}
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
}

