package in.nareshit.cva.service.impl;

import java.util.List;

import in.nareshit.cva.dao.impl.RequestStatusDaoImpl;
import in.nareshit.cva.model.RequestStatus;
import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.service.IRequestStatusService;

public class RequestStatusServiceImpl implements IRequestStatusService {

	@Override
	public Integer sendRequest(UploadFile uploadFile, String loginId) {
		return new RequestStatusDaoImpl().sendRequest(uploadFile, loginId);
	}

	@Override
	public List<UploadFile> getRequestsByLoginId(String loginId) {
		return new RequestStatusDaoImpl().getRequestsByLoginId(loginId);
	}

	@Override
	public Integer upadateStatus(RequestStatus requestStatus, String role) {
		return new RequestStatusDaoImpl().upadateStatus(requestStatus, role);
	}

	@Override
	public List<RequestStatus> getAllRequestsByAdmin() {
		return new RequestStatusDaoImpl().getAllRequestsByAdmin();
	}

}
