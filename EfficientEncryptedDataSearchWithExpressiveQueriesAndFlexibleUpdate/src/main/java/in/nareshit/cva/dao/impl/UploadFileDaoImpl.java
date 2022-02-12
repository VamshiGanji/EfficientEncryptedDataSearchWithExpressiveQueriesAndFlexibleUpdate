package in.nareshit.cva.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import in.nareshit.cva.dao.IUploadFileDao;
import in.nareshit.cva.model.UploadFile;
import in.nareshit.cva.util.DbConnection;
import in.nareshit.cva.util.FileEncryption;

public class UploadFileDaoImpl implements IUploadFileDao{


	@Override
	public Integer uploadFile(UploadFile uploadFile) {
		PreparedStatement pstmt;
		Integer result=0;

		String sql="insert into Storage_Node (file, fileName, fileDesc, fileKey, loginId) values(?, ?, ?, ?, ?)";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);

			System.out.println("---------------------Start----------------------");

			UploadFile uploadedFileWithDecrypt =  FileEncryption.encrypt(uploadFile.getFileKey(), uploadFile.getFile());
			uploadFile.setFile(uploadedFileWithDecrypt.getFile());
			uploadFile.setFileKey(uploadedFileWithDecrypt.getFileKey());

			/*	InputStream inputFileReader;
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		    int nRead;
		   // byte[] data = new byte[1024];
		    byte[] inputBytesReader = new byte[(int) uploadFile.getFile().available()];
		    while ((nRead = uploadFile.getFile().read(inputBytesReader, 0, inputBytesReader.length)) != -1) {
		        buffer.write(inputBytesReader, 0, nRead);
		    }
		    buffer.flush();
		    byte[] byteArray = buffer.toByteArray();
		    inputFileReader= new ByteArrayInputStream(byteArray);
			 */

			System.out.println("---------------------End----------------------");

			pstmt.setBlob(1, uploadFile.getFile());
			pstmt.setString(2, uploadFile.getFileName());
			pstmt.setString(3, uploadFile.getFileDesc());
			pstmt.setString(4, uploadFile.getFileKey());
			pstmt.setString(5, uploadFile.getLoginId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<UploadFile> getUploadedFile(UploadFile uploadFile) {
		List<UploadFile> uploadFiles=new ArrayList<UploadFile>();
		PreparedStatement pstmt;
		ResultSet rs;

		String sql="select *from Storage_Node where fileDesc like ?";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setString(1, '%'+uploadFile.getFileDesc().toUpperCase()+'%');
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UploadFile rsUploadFile = new UploadFile(rs.getInt("fid"), rs.getBinaryStream("file"), rs.getString("fileName"), rs.getString("fileDesc"),rs.getString("fileKey"), rs.getString("loginId"));
				rsUploadFile.setOldKey(new String(Base64.getDecoder().decode(rs.getString("fileKey"))));
				uploadFiles.add(rsUploadFile);
			}

			//uploadFiles.forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uploadFiles;
	}

	@Override
	public UploadFile getUploadedFileById(UploadFile uploadFile) {
		UploadFile uploadFileById=null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql="select *from Storage_Node where fid=?";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);
			pstmt.setInt(1, uploadFile.getFileId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("---------------------FileDecryption::Start----------------------");
				uploadFileById = new UploadFile(rs.getInt("fid"), rs.getBinaryStream("file"), rs.getString("fileName"), rs.getString("fileDesc"),rs.getString("fileKey"), rs.getString("loginId"));
				try {
					UploadFile uploadedFileWithDecrypt =  FileEncryption.decrypt(uploadFileById.getFileKey(), uploadFileById.getFile());
					uploadFileById.setFile(uploadedFileWithDecrypt.getFile());
					uploadFileById.setFileKey(uploadedFileWithDecrypt.getFileKey());

				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("---------------------FileDecryption::End----------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return uploadFile=null;
		}
		return uploadFileById;
	}

	@Override
	public List<UploadFile> getStorageNodeAllFiles() {
		List<UploadFile> uploadFiles=new ArrayList<UploadFile>();
		PreparedStatement pstmt;
		ResultSet rs;

		String sql="select *from Storage_Node";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UploadFile uploadFile = new UploadFile(rs.getInt("fid"), rs.getBinaryStream("file"), rs.getString("fileName"), rs.getString("fileDesc"),rs.getString("fileKey"), rs.getString("loginId"));
				uploadFile.setOldKey(new String(Base64.getDecoder().decode(rs.getString("fileKey"))));
				uploadFiles.add(uploadFile);
			}

			//uploadFiles.forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uploadFiles;
	}

	@Override
	public Integer updateFileKey(UploadFile uploadFile) {
		PreparedStatement pstmt;
		Integer result=0;

		String sql="update Storage_Node set file=? , fileKey=? , loginId=? where fid=?";

		try {
			pstmt = new DbConnection().getConnection().prepareStatement(sql);

			System.out.println("<<<<<<<<<<<<<-----Encryption::Start----->>>>>>>>>>>>>>>>>>>>>>>>");

			UploadFile uploadedFileWithDecrypt =  FileEncryption.encrypt(uploadFile.getFileKey(), uploadFile.getFile());
			uploadFile.setFile(uploadedFileWithDecrypt.getFile());
			uploadFile.setFileKey(uploadedFileWithDecrypt.getFileKey());

			System.out.println("<<<<<<<<<<<<<-----Decryption::Ended----->>>>>>>>>>>>>>>>>>>>>>>>");

			pstmt.setBlob(1, uploadFile.getFile());
			pstmt.setString(2, uploadFile.getFileKey());
			pstmt.setString(3, uploadFile.getLoginId());
			pstmt.setInt(4, uploadFile.getFileId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
