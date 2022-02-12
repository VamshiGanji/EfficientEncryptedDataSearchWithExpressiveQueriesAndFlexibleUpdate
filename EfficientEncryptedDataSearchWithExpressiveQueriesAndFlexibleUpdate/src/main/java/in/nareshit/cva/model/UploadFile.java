package in.nareshit.cva.model;

import java.io.InputStream;

public class UploadFile {

	private Integer fileId;
	private InputStream file;
	private String fileName;
	private String fileDesc;
	private String fileKey;
	private String loginId;
	private String oldKey;


	public UploadFile() {
		super();
	}
	//all params
	public UploadFile(Integer fileId, InputStream file, String fileName, String fileDesc, String fileKey,
			String loginId) {
		super();
		this.fileId = fileId;
		this.file = file;
		this.fileName = fileName;
		this.fileDesc = fileDesc;
		this.fileKey = fileKey;
		this.loginId = loginId;
	}
	public UploadFile(InputStream file, String fileName, String fileDesc, String fileKey, String loginId) {
		super();
		this.file = file;
		this.fileName = fileName;
		this.fileDesc = fileDesc;
		this.fileKey = fileKey;
		this.loginId = loginId;
	}

	// 2-param
	public UploadFile(String fileDesc) {
		super();
		this.fileDesc = fileDesc;
	}

	// 3-param
	public UploadFile(String fileDesc, String loginId, String fileKey) {
		super();
		this.fileDesc = fileDesc;
		this.loginId = loginId;
		this.fileKey = fileKey;
	}
	
	public UploadFile(Integer fileId, String fileKey, String loginId) {
		super();
		this.fileId = fileId;
		this.fileKey = fileKey;
		this.loginId = loginId;
	}
	public UploadFile(Integer fileId, String loginId) {
		super();
		this.fileId = fileId;
		this.loginId = loginId;
	}
	
	//1-param
	public UploadFile(Integer fileId) {
		super();
		this.fileId = fileId;
	}
	
	public Integer getFileId() {
		return fileId;
	}


	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}


	public InputStream getFile() {
		return file;
	}


	public void setFile(InputStream file) {
		this.file = file;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileDesc() {
		return fileDesc;
	}


	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}


	public String getFileKey() {
		return fileKey;
	}


	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getOldKey() {
		return oldKey;
	}
	
	public void setOldKey(String oldKey) {
		this.oldKey = oldKey;
	}
	
	@Override
	public String toString() {
		return "UploadFile [fileId=" + fileId + ", file=" + file + ", fileName=" + fileName + ", fileDesc=" + fileDesc
				+ ", fileKey=" + fileKey + ", loginId=" + loginId + "]";
	}
	

}
