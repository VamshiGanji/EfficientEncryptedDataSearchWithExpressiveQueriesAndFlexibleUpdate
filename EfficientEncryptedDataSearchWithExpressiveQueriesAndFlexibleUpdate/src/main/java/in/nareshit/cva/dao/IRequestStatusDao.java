package in.nareshit.cva.dao;

import java.util.List;

import in.nareshit.cva.model.RequestStatus;
import in.nareshit.cva.model.UploadFile;

public interface IRequestStatusDao {
	public Integer sendRequest(UploadFile uploadFile, String loginId);
	public List<UploadFile> getRequestsByLoginId(String loginId);
	public List<RequestStatus> getAllRequestsByAdmin();
	public Integer upadateStatus(RequestStatus requestStatus, String role);
}
