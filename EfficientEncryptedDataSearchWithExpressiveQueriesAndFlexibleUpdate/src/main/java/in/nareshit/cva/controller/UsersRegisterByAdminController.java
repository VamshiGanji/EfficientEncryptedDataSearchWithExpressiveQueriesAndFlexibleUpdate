package in.nareshit.cva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.User;
import in.nareshit.cva.service.impl.UserRegistrationServiceImpl;

@WebServlet("/regUsers")
public class UsersRegisterByAdminController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(UsersRegisterByAdminController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataOwnerRegisterByAdminController::doGet(req,resp)::started <<==================");
		req.getRequestDispatcher("./jsps/RegistersByAdminForm.jsp").forward(req, resp);
		logger.info("==================>> DataOwnerRegisterByAdminController::doGet(req,resp)::Ended <<==================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> DataOwnerRegisterByAdminController::doPost(req,resp)::started <<==================");

		User user = new User(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("loginId"), req.getParameter("password"), Long.parseLong(req.getParameter("mobile")), req.getParameter("role"), 1);

		//System.out.println("----------------> UserRegistrationController::User:: <---------------\n"+user);
		logger.info("------------------>> user:: "+ user +"<<------------------");

		if(new UserRegistrationServiceImpl().regUser(user)>0) {
			req.setAttribute("msg", "Registration Successfully !!");
			logger.info("------------------>> UserRegistrationController::doPost(req,resp)::Registration Successfully !! <<------------------");

		}else {
			req.setAttribute("msg", "Registration failed, Please try again !!");
			logger.info("------------------>> UserRegistrationController::doPost(req,resp)::Registration Failed !! <<------------------");
		}
		req.getRequestDispatcher("./jsps/RegistersByAdminForm.jsp").forward(req, resp);

		logger.info("==================>> DataOwnerRegisterByAdminController::doPost(req,resp)::Ended <<==================");
	}
}
