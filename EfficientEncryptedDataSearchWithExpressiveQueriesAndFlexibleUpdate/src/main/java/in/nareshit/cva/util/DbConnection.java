package in.nareshit.cva.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnection {
	static Connection connection;
	static Properties properties;

	private final static Logger logger= LoggerFactory.getLogger(DbConnection.class);

	public static Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Connection getConnection() {
		
		Properties p=getProperties();


		try {
			Class.forName(p.getProperty("driver"));
			connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("db.user"), p.getProperty("db.password"));
			
			//System.out.println("--------->Database Connection Created.!!<---------");
			logger.info("==================>> Database Connection Created.!! <<==================");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("==================>> Connection failed.!! <<==================");
		}
		return connection;
	}

}
