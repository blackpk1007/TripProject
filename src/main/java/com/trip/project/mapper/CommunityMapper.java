package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trip.project.dto.CommunityDTO;



@Mapper
public interface CommunityMapper {
	@Select(" SELECT * FROM COMMUNITY ORDER BY COMMUNITYNUMBER DESC ")
	List<CommunityDTO> selectCommunity();
	
	@Insert(" INSERT INTO COMMUNITY VALUES(NULL, #{communityTitle}, #{communityContent}, NOW(), #{communityCategory}) ")
	int insert(CommunityDTO dto);
	
	@Select(" SELECT * FROM COMMUNITY WHERE COMMUNITYNUMBER=#{communityNumber} ")
	CommunityDTO selectOne(int communityNumber);
	
	@Update(" UPDATE COMMUNITY SET COMMUNITYTITLE=#{communityTitle}, COMMUNITYCONTENT=#{communityContent}, COMMUNITYCATEGORY=#{communityCategory} ")
	int update(CommunityDTO dto);
	
	@Delete(" DELETE FROM COMMUNITY WHERE COMMUNITYNUMBER=#{communityNumber} ")
	int delete(int communityNumber);
}
