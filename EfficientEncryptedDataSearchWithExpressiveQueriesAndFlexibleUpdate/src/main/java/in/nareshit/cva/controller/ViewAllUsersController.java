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

import in.nareshit.cva.model.User;
import in.nareshit.cva.service.impl.UserRegistrationServiceImpl;

@WebServlet("/viewAllUsers")
public class ViewAllUsersController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(ViewAllUsersController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> ViewAllUsersController::doGet(req,resp)::started <<==================");

		List<User> viewAllUsers = new UserRegistrationServiceImpl().viewAllUsers();
		req.setAttribute("viewAllUsers", viewAllUsers);
		if (viewAllUsers.isEmpty() || viewAllUsers==null) 
			req.setAttribute("msg", "No records found !!");

		req.getRequestDispatcher("./jsps/ViewAllUsersByAdmin.jsp").forward(req, resp);

		logger.info("==================>> ViewAllUsersController::doGet(req,resp)::Ended <<==================");
	}

}
