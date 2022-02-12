package in.nareshit.cva.dao;

import java.util.List;

import in.nareshit.cva.model.UploadFile;

public interface IDatanodeCommunityDao {
	public UploadFile getDatanodeCommunityFileById(Integer fileFkId, String location);
	public Integer insertCount(Integer fileFkId, String location);
	public Integer getCount(Integer fileFkId, String location);
	public Integer insertDatanodeCommunityFileById(Integer fileFkId, String location);
	public List<UploadFile> getDatanodeCommunityFiles(String searchFile, String groupName);
}
