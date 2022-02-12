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

@WebServlet("/reqChange")
public class UpdateUserRequsetStausController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(UpdateUserRequsetStausController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> ViewUsersRequsetsController.java::doGet(req,resp)::started <<==================");

		String role = (String)req.getSession().getAttribute("role");

		RequestStatus requestStatus= new RequestStatus(Integer.parseInt(req.getParameter("rId")),  Integer.parseInt(req.getParameter("status")));				
		if (new RequestStatusServiceImpl().upadateStatus(requestStatus, (String)req.getSession().getAttribute("role"))>0) {
			
		}
		System.out.println("role:"+role);
		if (req.getParameter("status").equals("1")||req.getParameter("status").equals("0")) {
			if (new RequestStatusServiceImpl().upadateStatus(requestStatus, role)>0) {
				req.setAttribute("msg", "Status Changed !!");
			}
		}

		List<RequestStatus> requestsByAdmin =new ArrayList<RequestStatus>();
			requestsByAdmin= new RequestStatusServiceImpl().getAllRequestsByAdmin();

		if (!requestsByAdmin.isEmpty()) {
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

}
