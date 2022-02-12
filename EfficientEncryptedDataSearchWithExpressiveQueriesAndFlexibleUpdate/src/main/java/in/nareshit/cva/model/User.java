package in.nareshit.cva.model;

public class User {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String loginId;
	private String password;
	private Long mobile;
	private String role;
	private Integer status;
	

	public User() {
		super();
	}

	public User(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}

	
	//status update params
	public User(Integer userId, Integer status) {
		super();
		this.userId = userId;
		this.status = status;
	}

	public User(String firstName, String lastName, String loginId, String password, Long mobile, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginId = loginId;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
	}

	// All Params
	public User(Integer userId, String firstName, String lastName, String loginId, String password, Long mobile,
			String role, Integer status) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginId = loginId;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
		this.status = status;
	}

	// without userId, remaining params
	public User(String firstName, String lastName, String loginId, String password, Long mobile, String role, Integer status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginId = loginId;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", loginId=" + loginId
				+ ", password=" + password + ", mobile=" + mobile + ", role=" + role + ", status=" + status + "]";
	}
	
}
