package in.nareshit.cva.security;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebListener("application context listener")
//@WebListener
public class AppSecurity implements ServletContextListener {
	Logger logger=LoggerFactory.getLogger(AppSecurity.class);
		@Override
		public void contextInitialized(ServletContextEvent sce) {
		logger.info("<<==================>> requestInitialized::Started <<==================>>");

		logger.info("---------------> "+sce.getServletContext().getAttribute("role")+" <<-----------------");
		if (sce.getServletContext().getAttribute("role")==null) {
			//sre.getServletRequest().getRequestDispatcher("login");
			sce.getServletContext().getRequestDispatcher("./login");
		}


		logger.info("<<==================>> requestInitialized::Ended <<==================>>");
	}
		@Override
	public void contextDestroyed(ServletContextEvent sce) {
			logger.info("<<==================>> contextDestroyed::Ended <<==================>>");
	}

}
