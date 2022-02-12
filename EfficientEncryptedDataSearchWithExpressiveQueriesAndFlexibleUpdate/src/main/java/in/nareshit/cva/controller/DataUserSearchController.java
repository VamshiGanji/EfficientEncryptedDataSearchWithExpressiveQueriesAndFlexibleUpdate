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


@WebServlet("/search")
//@ServletSecurity(@HttpConstraint(rolesAllowed = "DataUser"))
public class DataUserSearchController extends HttpServlet{


	private static Logger logger = LoggerFactory.getLogger(DataUserSearchController.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataUserSearchController::doGet(req,resp)::started <<==================");
		req.getRequestDispatcher("./jsps/DataUserSearch.jsp").forward(req, resp);
		logger.info("==================>> DataUserSearchController::doGet(req,resp)::Ended <<==================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataUserSearchController::doPost(req,resp)::started <<==================");

		/* due to UI Bar Progress */
		try {
			new Thread();
			Thread.sleep(1000*7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<UploadFile> uploadFiles = new UploadFileServiceImpl().getUploadedFile(new UploadFile(req.getParameter("searchFile")));

		//uploadFiles.forEach(System.out::println);

		logger.info("-----------------------> uploadFiles:: "+uploadFiles+" <-----------------------");
		if (uploadFiles==null || uploadFiles.isEmpty()) 
			req.setAttribute("msg", "No Records found !!");
		else if(!uploadFiles.isEmpty()) {
			req.setAttribute("uploadFiles", uploadFiles);
		}else {
			req.setAttribute("uploadFiles", uploadFiles);
		}

		req.getRequestDispatcher("./jsps/DataUserSearch.jsp").forward(req, resp);

		logger.info("==================>> DataUserSearchController::doPost(req,resp)::Ended <<==================");
	}
}
