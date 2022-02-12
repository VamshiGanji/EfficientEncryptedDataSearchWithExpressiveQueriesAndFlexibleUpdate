package in.nareshit.cva.service.impl;

import java.util.List;

import in.nareshit.cva.dao.impl.UserRegistrationDaoImpl;
import in.nareshit.cva.model.User;
import in.nareshit.cva.service.IUserRegistrationService;

public class UserRegistrationServiceImpl implements IUserRegistrationService {

	@Override
	public Integer regUser(User user) {
		return new UserRegistrationDaoImpl().regUser(user);
	}

	@Override
	public List<User> viewAllUsers() {
		return new UserRegistrationDaoImpl().viewAllUsers();
	}
	
	@Override
	public List<User> viewAll_InActiveUsers() {
		return new UserRegistrationDaoImpl().viewAll_InActiveUsers();
	}

	@Override
	public Integer updateUserStatus(User user) {
		return new UserRegistrationDaoImpl().updateUserStatus(user);
	}

	@Override
	public Integer checkUserAvailable(String loginId) {
		return new UserRegistrationDaoImpl().checkUserAvailable(loginId);
	}

}
