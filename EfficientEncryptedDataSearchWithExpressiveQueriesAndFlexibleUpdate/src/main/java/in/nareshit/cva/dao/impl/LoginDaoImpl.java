package in.nareshit.cva.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.nareshit.cva.dao.ILoginDao;
import in.nareshit.cva.model.User;
import in.nareshit.cva.util.DbConnection;

public class LoginDaoImpl implements ILoginDao {

	public User loginCheck(User user) {
		User user2=new User();

		/*This is the SQL Query*/
		String sql="select role from users where loginId=? and password=? and status=?";

		try {
			/* Creating the Database Connection With prepareStatement */
			PreparedStatement pstmt = new DbConnection().getConnection().prepareStatement(sql);

			/* Set/Add the runtime parameter to SQL Query */
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, 1);

			/* Execute the PreparedStatement and the result stored in resultSet object */
			ResultSet resultSet = pstmt.executeQuery();

			/* fetch the results .next(); */
			resultSet.next();

			/* read/set the role to userRole variable */
			user2.setRole(resultSet.getString("role"));

			//System.out.println("----------------> LoginDaoImpl::userRole::"+ userRole +" <---------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user2;
	}

}
