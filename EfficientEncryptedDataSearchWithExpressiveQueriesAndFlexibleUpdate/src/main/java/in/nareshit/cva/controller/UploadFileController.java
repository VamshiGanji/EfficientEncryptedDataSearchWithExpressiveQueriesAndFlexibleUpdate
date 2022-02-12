package in.nareshit.cva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.service.impl.UploadFileServiceImpl;


@WebServlet("/uploadFile")
//@MultipartConfig
//@MultipartConfig(maxFileSize = 429496729) // 16MB //4GB 4294967295 52428800
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 50, // 50 MB
maxRequestSize = 1024 * 1024 * 100)      // 100 MB
public class UploadFileController extends HttpServlet{


	private static Logger logger = LoggerFactory.getLogger(UploadFileController.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> UploadFileController::doGet(req,resp)::started <<==================");
		req.getRequestDispatcher("./jsps/UploadFile.jsp").forward(req, resp);
		logger.info("==================>> UploadFileController::doGet(req,resp)::Ended <<==================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> UploadFileController::doPost(req,resp)::started <<==================");
		/* due to UI Bar Progress */
		try {
			new Thread();
			Thread.sleep(1000*7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Integer flag = new UploadFileServiceImpl().uploadFile(new UploadFile(req.getPart("uploadFile").getInputStream(), req.getPart("uploadFile").getSubmittedFileName(), req.getParameter("fileDesc").toUpperCase(), req.getParameter("fileKey"), (String)req.getSession().getAttribute("loginId")));
		if (flag>0) 
			req.setAttribute("msg", "File Uploaded successfully !!");
		else 
			req.setAttribute("msg", "File Uploading failed !!");

		req.getRequestDispatcher("./jsps/UploadFile.jsp").forward(req, resp);

		logger.info("==================>> UploadFileController::doPost(req,resp)::Ended <<==================");
	}
}
