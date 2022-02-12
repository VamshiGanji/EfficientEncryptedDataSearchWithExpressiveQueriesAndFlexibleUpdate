package in.nareshit.cva.dao;

import java.util.List;

import in.nareshit.cva.model.UploadFile;

public interface IUploadFileDao {
	public Integer uploadFile(UploadFile uploadFile);
	public List<UploadFile> getUploadedFile(UploadFile uploadFile);
	public UploadFile getUploadedFileById(UploadFile uploadFile);
	public List<UploadFile> getStorageNodeAllFiles();
	public Integer updateFileKey(UploadFile uploadFile);
}
