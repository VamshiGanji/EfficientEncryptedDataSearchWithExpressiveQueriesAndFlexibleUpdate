package in.nareshit.cva.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.User;
import in.nareshit.cva.service.impl.LoginServiceImpl;
import in.nareshit.cva.service.impl.UserRegistrationServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> LoginController::doGet(req,resp)::started <<==================");
		String role = (String) req.getSession().getAttribute("role");
		logger.info("------------------>> LoginController::doGet(req,resp)::role:: ",role+" <<------------------");

		if(role!=null && role.equals("Admin"))
			req.getRequestDispatcher("./jsps/AdminHome.jsp").forward(req, resp);
		else if(role!=null && role.equals("DataOwner"))
			req.getRequestDispatcher("./jsps/DataOwnerHome.jsp").forward(req, resp);
		else if(role!=null && role.equals("DataUser"))
			req.getRequestDispatcher("./jsps/DataUserHome.jsp").forward(req, resp);
		else
			req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);

		logger.info("==================>> LoginController::doGet(req,resp)::Ended <<==================");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> LoginController::doPost(req,resp)::started <<==================");
		resp.setContentType("text/html");
		//PrintWriter out=resp.getWriter();

		//System.out.println("==========> userId : "+req.getParameter("loginId")+ ", Pwd: " +req.getParameter("password"));
		logger.info("------------------>> userId: "+req.getParameter("loginId")+", Pwd: "+ req.getParameter("password")+" <<------------------");
		User user= new LoginServiceImpl().loginCheck(new User(req.getParameter("loginId"), req.getParameter("password")));

		//System.out.println("----------------> LoginController::userRole::"+ userRole +" <---------------");
		logger.info("------------------>> userRole: "+user.getRole() +" <<------------------");

		if (user.getRole()!=null) {
			HttpSession session=req.getSession();
			session.setAttribute("loginId", req.getParameter("loginId"));
			session.setAttribute("role", user.getRole());

			if(req.getSession().getAttribute("role")!=null && req.getSession().getAttribute("role").equals("Admin"))
				req.getRequestDispatcher("./jsps/AdminHome.jsp").forward(req, resp);
			else if(req.getSession().getAttribute("role")!=null && req.getSession().getAttribute("role").equals("DataOwner"))
				req.getRequestDispatcher("./jsps/DataOwnerHome.jsp").forward(req, resp);
			else if(req.getSession().getAttribute("role")!=null && req.getSession().getAttribute("role").equals("DataUser"))
				req.getRequestDispatcher("./jsps/DataUserHome.jsp").forward(req, resp);
			else
				req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);
		}else {
			/* If the user is inActive list, response message is different */
			List<User> viewAll_InActiveUsers = new UserRegistrationServiceImpl().viewAll_InActiveUsers();
			viewAll_InActiveUsers.forEach(e->{
				if ((e.getLoginId().equals(req.getParameter("loginId")) && (e.getPassword().equals(req.getParameter("password")))))
					req.setAttribute("msg", "Waiting for Admin Aceptance !!");
				else
					req.setAttribute("msg", "Invalid UserId & Password!!");
			});

			if (user.getRole()==null && viewAll_InActiveUsers.isEmpty()) 
				req.setAttribute("msg", "Please register the user !!");

			req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);
		}

		logger.info("==================>> LoginController::doPost(req,resp)::Ended <<==================");
	}
}
