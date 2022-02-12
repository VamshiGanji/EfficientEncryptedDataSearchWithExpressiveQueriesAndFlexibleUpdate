package in.nareshit.cva.service;

import java.util.List;

import in.nareshit.cva.model.User;

public interface IUserRegistrationService {
	public Integer regUser(User user);
	public List<User> viewAllUsers();
	public List<User> viewAll_InActiveUsers();
	public Integer updateUserStatus(User user);
	public Integer checkUserAvailable(String loginId);
}
