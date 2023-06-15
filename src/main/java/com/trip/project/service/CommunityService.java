package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.dto.ImageDTO;
import com.trip.project.dto.UploadFile;

public interface CommunityService {
	public List<CommunityDTO> selectCommunity();
	public int insert(CommunityDTO dto);
	public CommunityDTO selectOne(int communityNumber);
	public int update(CommunityDTO dto);
	public int delete(int communityNumber);
	
	public CommunityDTO ComunityselectOne();
	public int imageInsert(UploadFile image);
	public List<ImageDTO> selectImage();
	public ImageDTO selectOneImg(int imageNumber);
	
	public List<CommunityDTO> selectCommunityCategory(String communityCategory);
	
}
