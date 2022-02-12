package in.nareshit.cva.service.impl;

import in.nareshit.cva.dao.impl.LoginDaoImpl;
import in.nareshit.cva.model.User;
import in.nareshit.cva.service.ILoginService;

public class LoginServiceImpl implements ILoginService {

	public User loginCheck(User user) {
		return new LoginDaoImpl().loginCheck(user);
	}

}
