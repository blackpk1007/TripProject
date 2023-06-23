package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.dto.ImageDTO;
import com.trip.project.dto.SearchDTO;
import com.trip.project.dto.UploadFile;
import com.trip.project.paging.PagingResponse;

public interface CommunityService {
	public PagingResponse<CommunityDTO> selectCommunity(final SearchDTO params);
	public int insert(CommunityDTO dto);
	public CommunityDTO selectOne(int communityNumber);
	public int update(CommunityDTO dto);
	public int delete(int communityNumber);
	
	public CommunityDTO ComunityselectOne();
	public int imageInsert(UploadFile image);
	public List<ImageDTO> selectImage();
	public ImageDTO selectOneImg(int imageNumber);
	public int updateImg(UploadFile image);
	
	public PagingResponse<CommunityDTO> selectCommunityTip(String communityCategory, SearchDTO params);
	public PagingResponse<CommunityDTO> selectCommunityReview(String communityCategory, SearchDTO params);
	
	public int Count(SearchDTO params); //게시글 수 카운팅
	
	// 마이페이지에 게시물 정보 들고오기
	public List<CommunityDTO> usermainCommunity(String userID);
}
