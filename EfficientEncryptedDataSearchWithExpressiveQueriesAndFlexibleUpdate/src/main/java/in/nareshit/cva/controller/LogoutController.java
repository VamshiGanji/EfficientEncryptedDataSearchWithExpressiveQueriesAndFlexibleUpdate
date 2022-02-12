package in.nareshit.cva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

	private static Logger logger= LoggerFactory.getLogger(LogoutController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> LogoutController::doGet(req,resp)::started <<==================");

		HttpSession session= req.getSession();
		logger.info("------------------>> role:: invalidated() "+ session.getAttribute("role") +"<<------------------");

		session.invalidate();
		req.getSession(false);

		//System.out.println("--->msg:: "+req.getAttribute("msg"));
		if(req.getAttribute("msg")!=null)
			req.setAttribute("msg", req.getAttribute("msg"));
		else
			req.setAttribute("msg", "You have been successfully logged out!!");
		req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);

		logger.info("==================>> LogoutController::doGet(req,resp)::Ended <<==================");
	}
}
