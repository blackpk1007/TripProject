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
	@Select(" SELECT * FROM community ORDER BY communityNumber DESC ")
	List<CommunityDTO> selectCommunity();
	
	@Insert(" INSERT INTO community VALUES(NULL, #{communityTitle}, #{communityContent}, NOW(), #{communityCategory}) ")
	int insert(CommunityDTO dto);
	
	@Select(" SELECT * FROM community WHERE communityNumber=#{communityNumber} ")
	CommunityDTO selectOne(int communityNumber);
	

	@Update(" UPDATE community SET communityTitle=#{communityTitle}, communityContent=#{communityContent}, communityCategory=#{communityCategory} WHERE communityNumber=#{communityNumber} ")

	int update(CommunityDTO dto);
	
	@Delete(" DELETE FROM community WHERE communityNumber=#{communityNumber} ")
	int delete(int communityNumber);
}
