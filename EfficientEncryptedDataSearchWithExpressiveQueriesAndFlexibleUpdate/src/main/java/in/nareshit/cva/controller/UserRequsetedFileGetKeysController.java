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
import in.nareshit.cva.service.impl.RequestStatusServiceImpl;

@WebServlet("/getKeys")
public class UserRequsetedFileGetKeysController extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(UserRequsetedFileGetKeysController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("==================>> UserRequsetedFileKeysController.java::doGet(req,resp)::started <<==================");
		
		logger.info("------------------------------->loginId:: "+req.getSession().getAttribute("loginId")+" <----------------------");
		
		
		List<UploadFile> getKeys = new RequestStatusServiceImpl().getRequestsByLoginId((String)req.getSession().getAttribute("loginId"));

		//getKeys.forEach(System.out::println);
		
		if (!getKeys.isEmpty()) {
			logger.info("-------------------> requested keys <-----------------------");
			getKeys.forEach(e->{logger.info("-------------------> "+e +"  <-----------------------");});
			req.setAttribute("getKeys", getKeys);
		}else {
			logger.info("-------------------> No records found !! <-----------------------");
			req.setAttribute("msg", "No records found !!");
		}
		
		if(req.getSession().getAttribute("role").equals("DataUser"))
			req.getRequestDispatcher("./jsps/ViewUsersRequestedKeysByUser.jsp").forward(req, resp);
		else {
			req.setAttribute("msg", "Please Enter UserId & Password !!");
			req.getRequestDispatcher("./jsps/Login.jsp").forward(req, resp);
		}

		logger.info("==================>> UserRequsetedFileKeysController::doGet(req,resp)::Ended <<==================");
	}

}
