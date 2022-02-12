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

import in.nareshit.cva.model.User;
import in.nareshit.cva.service.impl.UserRegistrationServiceImpl;

@WebServlet("/inActiveUsers")
public class ViewAll_InActiveUsersController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(ViewAll_InActiveUsersController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> ViewAll_InActiveUsersController.java::doGet(req,resp)::started <<==================");

		List<User> viewAll_InActiveUsers = new UserRegistrationServiceImpl().viewAll_InActiveUsers();
		req.setAttribute("inActiveUsers", viewAll_InActiveUsers);
		if (viewAll_InActiveUsers.isEmpty() || viewAll_InActiveUsers==null) 
			req.setAttribute("msg", "No records found !!");

		req.getRequestDispatcher("./jsps/ViewAll_InActiveUsersByAdmin.jsp").forward(req, resp);

		logger.info("==================>> ViewAll_InActiveUsersController::doGet(req,resp)::Ended <<==================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> ViewAll_InActiveUsersController.java::doPost(req,resp)::started <<==================");
		
		Integer status = new UserRegistrationServiceImpl().updateUserStatus(new User(Integer.parseInt(req.getParameter("userId")), Integer.parseInt(req.getParameter("status"))));
		List<User> viewAllUsers=new ArrayList<User>();
		
		if (status>0 && Integer.parseInt(req.getParameter("status"))==0) {
			viewAllUsers = new UserRegistrationServiceImpl().viewAllUsers();
			req.setAttribute("viewAllUsers", viewAllUsers);
			req.setAttribute("msg", "User is De-activated !!");
			req.getRequestDispatcher("./jsps/ViewAllUsersByAdmin.jsp").forward(req, resp);
		}
		if (status>0 && Integer.parseInt(req.getParameter("status"))==1) {
			viewAllUsers = new UserRegistrationServiceImpl().viewAll_InActiveUsers();
			req.setAttribute("inActiveUsers", viewAllUsers);
			req.setAttribute("msg", "User is Activated !!");
			req.getRequestDispatcher("./jsps/ViewAll_InActiveUsersByAdmin.jsp").forward(req, resp);
		}
		
		
		logger.info("==================>> ViewAll_InActiveUsersController::doPost(req,resp)::Ended <<==================");
	}
}
