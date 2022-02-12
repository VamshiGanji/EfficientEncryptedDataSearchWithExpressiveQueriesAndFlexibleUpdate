package in.nareshit.cva.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.RequestStatus;
import in.nareshit.cva.service.impl.RequestStatusServiceImpl;

@WebServlet("/usersReqsByAdmin")
public class ViewUsersRequsetsByAdminController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(ViewUsersRequsetsByAdminController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> ViewUsersRequsetsController.java::doGet(req,resp)::started <<==================");
		logger.info("------------------------------->loginId:: "+req.getSession().getAttribute("loginId")+" <----------------------");
		
		List<RequestStatus> requestsByAdmin =new ArrayList<RequestStatus>();
		requestsByAdmin= new RequestStatusServiceImpl().getAllRequestsByAdmin();

		requestsByAdmin.forEach(System.out::println);
		if (!requestsByAdmin.isEmpty()) {
			logger.info("-------------------> requestsByAdmin <-----------------------");
			requestsByAdmin.forEach(e->{logger.info("-------------------> "+e +"  <-----------------------");});
			req.setAttribute("requestsByAdmin", requestsByAdmin);
		}else {
			logger.info("-------------------> No records found !! <-----------------------");
			req.setAttribute("msg", "No records found !!");
		}
		
		if (req.getSession().getAttribute("role").equals("Admin")) 
			req.getRequestDispatcher("./jsps/ViewUsersRequestsByAdmin.jsp").forward(req, resp);
		else if(req.getSession().getAttribute("role").equals("DataOwner"))
			req.getRequestDispatcher("./jsps/ViewUsersRequestsByDataOwner.jsp").forward(req, resp);
		else {
			req.setAttribute("msg", "Please Enter UserId & Password !!");
			req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);
		}

		logger.info("==================>> ViewUsersRequsetsController::doGet(req,resp)::Ended <<==================");
	}
/*
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> ViewUsersRequsetsController.java::doPost(req,resp)::started <<==================");
		String loginId=(String) req.getSession().getAttribute("loginId");
		Integer fileId = Integer.parseInt(req.getParameter("fileId"));
		String enteredfileKey=req.getParameter("fileKey");
		UploadFile uploadFile = new UploadFile(fileId, enteredfileKey, loginId);
		if (new RequestStatusServiceImpl().sendRequest(uploadFile, loginId)>0) {
			
		}

		logger.info("==================>> ViewUsersRequsetsController::doPost(req,resp)::Ended <<==================");
	}
*/
}
