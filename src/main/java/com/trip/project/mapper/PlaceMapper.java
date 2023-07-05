package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.RecommandDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.dto.PlanDetailDTO;
import com.trip.project.dto.placePagination;

@Mapper
public interface PlaceMapper {
	
	@Insert(" insert into place values(null, #{placeName}, #{placeAddress}, "
			+ "#{placeCategory}, #{placePhone}, #{placeGood}, #{placeContent}, "
			+ "#{placeInfo}, #{placeTag1}, #{placeTag2}, #{placeTag3}, #{placeLon}, #{placeLat}) ")
	int crawinsert(PlaceDTO dto);
	
	// 모든 장소 좌표 마커
	@Select(" select * from place where placeCategory = #{category} order by placeGood desc limit ${paging.getSkip()}, ${paging.getSize()}" )
	List<PlaceDTO> placeCategoryMarker(@Param("category")String category, @Param("paging")placePagination paging);
	
	@Select(" select count(*) from place where placeCategory = #{category} " )
	int placeCategoryCount(@Param("category")String category);
	
	// 맛집 정보
	@Select(" select * from place where placeCategory = 'restaurant' order by placeGood desc limit ${paging.getSkip()}, ${paging.getSize()}")
	List<PlaceDTO> placeRestaurantList(@Param("paging")placePagination paging);

	@Select(" select count(*) from place where placeCategory = 'restaurant' order by placeGood")
	int placeRestaurantListCount();
	
	@Select(" select * from place ")
	List<PlaceDTO> placeList();
	
	@Select(" select * from place where placeName like '%${keyword}%' order by placeGood desc limit ${paging.getSkip()}, ${paging.getSize()}")
	List<PlaceDTO> placeSearch(@Param("keyword") String keyword, @Param("paging")placePagination paging);
	
	@Select(" SELECT l.userGender, recommandPlaceNumber, COUNT(*) AS count FROM login AS l JOIN recommand AS r ON l.userID = r.recommandUserID WHERE r.recommandPlaceNumber =#{recommandPlaceNumber} GROUP BY l.userGender ")
	List<LoginDTO> genderList(int recommandPlaceNumber);
	
	@Select(" SELECT DATE_FORMAT(l.userBirth, '%Y') AS userBirth, r.recommandPlaceNumber FROM login AS l JOIN recommand AS r ON l.userID = r.recommandUserID WHERE r.recommandPlaceNumber =#{recommandPlaceNumber} ")
	List<LoginDTO> birthList(int recommandPlaceNumber);
	
	// 마이페이지에 갯수세는거 
	@Select(" SELECT * FROM recommand WHERE recommandUserID=#{userID}" )
	public List<RecommandDTO> usermainRecommand(String userID);	

	@Insert(" insert into plan values(null, #{userID}, #{planName}, #{planFirstDate}, #{planLastDate}, default) ")
	int planInsert(PlanDTO dto);
	
	@Insert(" insert into planDetail values(null, #{userID}, #{planName}, #{planDetailDate}, #{planDetailLon}, #{planDetailLat}, #{planDetailColor}) ")
	int planDetailInsert(PlanDetailDTO dto);
	
	@Select(" select * from plan where userID = #{userID} ")
	PlanDTO userPlan(String userID);
	
	//마이페이지 갯수
	@Select(" select * from plan where userID = #{userID} ")
	public List<PlanDTO> userPlancount(String userID);
	
	@Select(" select * from planDetail where userID = #{userID} and planName = #{planName} ")
	List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto);
	
	@Delete(" delete from plan where userID = #{userID} and planName = #{planName} ")
	int planDelete(String userID, String planName);
	
}
