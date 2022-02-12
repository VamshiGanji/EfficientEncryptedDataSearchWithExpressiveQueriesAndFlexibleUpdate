package in.nareshit.cva.dao;

import java.util.List;

import in.nareshit.cva.model.User;

public interface IUserRegistrationDao {
	public Integer regUser(User user);
	public List<User> viewAllUsers();
	public List<User> viewAll_InActiveUsers();
	public Integer updateUserStatus(User user);
	public Integer checkUserAvailable(String loginId);
}
