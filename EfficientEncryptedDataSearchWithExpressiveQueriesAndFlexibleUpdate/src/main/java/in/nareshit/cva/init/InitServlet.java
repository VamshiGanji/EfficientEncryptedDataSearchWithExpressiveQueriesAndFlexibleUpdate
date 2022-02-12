package in.nareshit.cva.init;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import in.nareshit.cva.util.DbConnection;

public class InitServlet extends HttpServlet {
	DbConnection dbConn;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		
		dbConn= new DbConnection();
		
		InputStream inputStream = sc.getResourceAsStream(config.getInitParameter("config"));
		
		Properties props=new Properties();
		
		try {
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dbConn.setProperties(props);
		
		System.out.println(props.getProperty("driver"));
		System.out.println(props.getProperty("db.user"));
		System.out.println(props.getProperty("db.password"));
		
		
		
		ServletContext context = config.getServletContext();
		String log4jConfigFile = config.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") +File.separator + log4jConfigFile;
		System.out.println(fullPath);
		
		PropertyConfigurator.configure(fullPath);
	}
	
}
