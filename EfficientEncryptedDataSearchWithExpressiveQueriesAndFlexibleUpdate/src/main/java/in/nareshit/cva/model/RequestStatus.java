package in.nareshit.cva.model;

public class RequestStatus {
	private Integer rId;
	private Integer fid;
	private String entrdKey;
	private String loginId;
	private Integer adminStaus;

	public RequestStatus() {
		super();
	}

	public RequestStatus(Integer rId, Integer fid, String entrdKey, String loginId, Integer adminStaus) {
		super();
		this.rId = rId;
		this.fid = fid;
		this.entrdKey = entrdKey;
		this.loginId = loginId;
		this.adminStaus = adminStaus;
	}

	public RequestStatus(Integer fid, String entrdKey, String loginId, Integer adminStaus) {
		super();
		this.fid = fid;
		this.entrdKey = entrdKey;
		this.loginId = loginId;
		this.adminStaus = adminStaus;
	}

	public RequestStatus(Integer rId, Integer adminStaus) {
		super();
		this.rId = rId;
		this.adminStaus = adminStaus;
	}

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getEntrdKey() {
		return entrdKey;
	}

	public void setEntrdKey(String entrdKey) {
		this.entrdKey = entrdKey;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Integer getAdminStaus() {
		return adminStaus;
	}

	public void setAdminStaus(Integer adminStaus) {
		this.adminStaus = adminStaus;
	}

	@Override
	public String toString() {
		return "RequestStatus [rId=" + rId + ", fid=" + fid + ", entrdKey=" + entrdKey + ", loginId=" + loginId
				+ ", adminStaus=" + adminStaus + "]";
	}

}
