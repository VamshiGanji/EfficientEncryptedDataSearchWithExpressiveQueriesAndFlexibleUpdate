package in.nareshit.cva.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.nareshit.cva.dao.IUserRegistrationDao;
import in.nareshit.cva.model.User;
import in.nareshit.cva.util.DbConnection;

public class UserRegistrationDaoImpl implements IUserRegistrationDao {

	public Integer regUser(User user) {
		String sql= "insert into users (firstName, lastName, loginId, password, mobile, role, status) values (?, ?, ?, ?, ?, ?, ?)";

		int count = 0;
		try {

			PreparedStatement pstmt;
			pstmt = new DbConnection().getConnection().prepareStatement(sql);

			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getLoginId());
			pstmt.setString(4, user.getPassword());
			pstmt.setLong(5, user.getMobile());
			pstmt.setString(6, user.getRole());
			pstmt.setInt(7, user.getStatus());

			count = pstmt.executeUpdate();
			System.out.println("-------->regUser(user)::count:: "+count + " <----------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}


	public List<User> viewAllUsers() {
		List<User> users=new ArrayList<User>();

		/*This is the SQL Query*/
		String sql="select * from users where role=? and status=?";

		try {
			/* Creating the Database Connection With prepareStatement */
			PreparedStatement pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setString(1, "DataUser");
			pstmt.setInt(2, 1);

			/* Execute the PreparedStatement and the result stored in resultSet object */
			ResultSet resultSet = pstmt.executeQuery();

			/* fetch the results .next(); */
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("userId"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("loginId"), resultSet.getString("password"), resultSet.getLong("mobile"), resultSet.getString("role"), resultSet.getInt("status")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> viewAll_InActiveUsers() {
		List<User> users=new ArrayList<User>();

		/*This is the SQL Query*/
		String sql="select * from users where role=? and status=?";

		try {
			/* Creating the Database Connection With prepareStatement */
			PreparedStatement pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setString(1, "DataUser");
			pstmt.setInt(2, 0);

			/* Execute the PreparedStatement and the result stored in resultSet object */
			ResultSet resultSet = pstmt.executeQuery();

			/* fetch the results .next(); */
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("userId"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("loginId"), resultSet.getString("password"), resultSet.getLong("mobile"), resultSet.getString("role"), resultSet.getInt("status")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}


	@Override
	public Integer updateUserStatus(User user) {
		String sql= "update users set status=? where userId=?";

		int count = 0;
		try {

			PreparedStatement pstmt;
			pstmt = new DbConnection().getConnection().prepareStatement(sql);

			pstmt.setInt(1, user.getStatus());
			pstmt.setInt(2, user.getUserId());

			count = pstmt.executeUpdate();
			System.out.println("-------->regUser(user)::count:: "+count + " <----------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}


	@Override
	public Integer checkUserAvailable(String loginId) {
		Integer flag=0;

		/*This is the SQL Query*/
		String sql="select * from users where loginId=?";

		try {
			/* Creating the Database Connection With prepareStatement */
			PreparedStatement pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setString(1, loginId);

			/* Execute the PreparedStatement and the result stored in resultSet object */
			ResultSet resultSet = pstmt.executeQuery();

			/* fetch the results .next(); */
			if (resultSet.next())
				flag=1;
			else 
				flag=0;

		} catch (SQLException e) {
			flag=0;
			e.printStackTrace();
		}
		return flag;
	}

}
