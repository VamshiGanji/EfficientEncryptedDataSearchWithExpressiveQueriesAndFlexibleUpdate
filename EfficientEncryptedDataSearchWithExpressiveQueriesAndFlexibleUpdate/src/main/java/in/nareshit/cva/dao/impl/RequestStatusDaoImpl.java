package in.nareshit.cva.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.nareshit.cva.dao.IRequestStatusDao;
import in.nareshit.cva.model.RequestStatus;
import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.util.DbConnection;
import in.nareshit.cva.util.FileEncryption;

public class RequestStatusDaoImpl implements IRequestStatusDao {

	@Override
	public Integer sendRequest(UploadFile uploadFile, String loginId) {
		Integer count=0;

		PreparedStatement pstmt;
		String sql="insert into reqstatus (fid, entrdKey, loginId) values (?, ?, ?)";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setInt(1, uploadFile.getFileId());
			pstmt.setString(2, uploadFile.getFileKey());
			pstmt.setString(3, loginId);

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<UploadFile> getRequestsByLoginId(String loginId) {
		List<UploadFile> requestStatuses =new ArrayList<UploadFile>();
		UploadFile uploadFileById=null;

		PreparedStatement pstmt;
		String sql="select n.* from storage_node n, reqstatus r, users u where u.loginId=? and  r.adminStatus=? and n.fid=r.fid and u.loginId=r.loginId group by n.fid order by n.fid";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setInt(2, 1);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("---------------------FileDecryption::Start----------------------");
				uploadFileById = new UploadFile(rs.getInt("n.fid"), rs.getBinaryStream("n.file"), rs.getString("n.fileName"), rs.getString("n.fileDesc"),rs.getString("n.fileKey"), rs.getString("n.loginId"));
				System.out.println("uploadFileById"+uploadFileById);
				try {
					UploadFile uploadedFileWithDecrypt =  FileEncryption.decrypt(uploadFileById.getFileKey(), uploadFileById.getFile());
					uploadFileById.setFile(uploadedFileWithDecrypt.getFile());
					uploadFileById.setFileKey(uploadedFileWithDecrypt.getFileKey());
					requestStatuses.add(uploadFileById);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requestStatuses;
	}

	@Override
	public List<RequestStatus> getAllRequestsByAdmin() {
		List<RequestStatus> requestStatuses =new ArrayList<RequestStatus>();

		PreparedStatement pstmt;
		String sql="select * from reqstatus";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				requestStatuses.add(new RequestStatus(rs.getInt("rid"), rs.getInt("fid"), rs.getString("entrdKey"), rs.getString("loginId"), rs.getInt("adminStatus")));
			}

			for (RequestStatus requestStatus : requestStatuses) {
				System.out.println("---------------------->>>>>>>> "+requestStatus +" <<<<<<<<<<--------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requestStatuses;
	}

	@Override
	public Integer upadateStatus(RequestStatus requestStatus, String role) {
		Integer count=0;

		String sql=null;
		PreparedStatement pstmt;
		if (role.equals("Admin")) 
			sql="update reqstatus set adminStatus=? where rid=?";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);

			if (role.equals("Admin")) {
				pstmt.setInt(1, requestStatus.getAdminStaus());
				pstmt.setInt(2, requestStatus.getrId());
				count = pstmt.executeUpdate();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
