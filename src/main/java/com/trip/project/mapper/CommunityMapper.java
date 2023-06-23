package com.trip.project.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.dto.ImageDTO;
import com.trip.project.dto.SearchDTO;
import com.trip.project.dto.UploadFile;
import com.trip.project.paging.PagingResponse;



@Mapper
public interface CommunityMapper {
	@Select(" SELECT * FROM community ORDER BY communityNumber DESC LIMIT #{pagination.limitStart}, #{recordSize} ")
	PagingResponse<CommunityDTO> selectCommunity(SearchDTO params);
	
	@Select(" SELECT * FROM community ORDER BY communityNumber DESC LIMIT #{pagination.limitStart}, #{recordSize} ")
	List<CommunityDTO> selectCommunityList(SearchDTO params);
	
	@Select(" SELECT COUNT(*) FROM community ")
	int Count(SearchDTO params);
	
	@Insert(" INSERT INTO community VALUES(NULL, #{communityTitle}, #{communityContent}, NOW(), #{communityCategory}) ")
	int insert(CommunityDTO dto);
	
	@Select(" SELECT * FROM community WHERE communityNumber=#{communityNumber} ")
	CommunityDTO selectOne(int communityNumber);
	

	@Update(" UPDATE community SET communityTitle=#{communityTitle}, communityContent=#{communityContent}, communityCategory=#{communityCategory} WHERE communityNumber=#{communityNumber} ")
	int update(CommunityDTO dto);
	
	@Delete(" DELETE FROM community WHERE communityNumber=#{communityNumber} ")
	int delete(int communityNumber);
	
	@Select(" SELECT * FROM community ORDER BY communityNumber DESC LIMIT 1 ")
	CommunityDTO ComunityselectOne();
	
	@Insert(" INSERT INTO communityimage VALUES(#{imageNumber}, NOW(), #{imageOriginalName}, #{imageStoredName}) ")
	int imageInsert(UploadFile image);
	
	@Select(" SELECT * FROM communityimage ")
	List<ImageDTO> selectImage();
	
	@Select(" SELECT * FROM communityimage WHERE imageNumber=#{communityNumber} ")
	ImageDTO selectOneImg(int imageNumber);
	
	@Update(" UPDATE communityimage SET imageCreateDate=NOW(), imageOriginalName=#{imageOriginalName}, imageStoredName=#{imageStoredName} WHERE imageNumber=#{imageNumber} " )
	int updateImg(UploadFile image);
	
	@Select( "SELECT * FROM community  WHERE communityCategory=#{communityCategory} ORDER BY communityNumber DESC LIMIT #{pagination.limitStart}, #{recordSize} " )
	PagingResponse<CommunityDTO> selectCommunityCategory(String communityCategory, SearchDTO params);
	
	@Select( "SELECT * FROM community  WHERE communityCategory=#{communityCategory} ORDER BY communityNumber DESC LIMIT #{params.pagination.limitStart}, #{params.recordSize} " )
	List<CommunityDTO> selectCommunityCategoryList(String communityCategory, SearchDTO params);
	
	// 마이페이지에 내 게시물 정보 들고오
	@Select( " SELECT * FROM community WHERE communityWriter=#{userID} " )
	List<CommunityDTO> usermainCommunity(String userID);
	
	
}
