package in.nareshit.cva.service.impl;

import java.util.List;

import in.nareshit.cva.dao.impl.UploadFileDaoImpl;
import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.service.IUploadFileService;

public class UploadFileServiceImpl implements IUploadFileService{

	@Override
	public Integer uploadFile(UploadFile uploadFile) {
		return new UploadFileDaoImpl().uploadFile(uploadFile);
	}

	@Override
	public List<UploadFile> getUploadedFile(UploadFile uploadFile) {
		return new UploadFileDaoImpl().getUploadedFile(uploadFile);
	}

	@Override
	public UploadFile getUploadedFileById(UploadFile uploadFile) {
		return new UploadFileDaoImpl().getUploadedFileById(uploadFile);
	}

	@Override
	public List<UploadFile> getStorageNodeAllFiles() {
		return new UploadFileDaoImpl().getStorageNodeAllFiles();
	}

	@Override
	public Integer updateFileKey(UploadFile uploadFile) {
		return new UploadFileDaoImpl().updateFileKey(uploadFile);
	}
}
