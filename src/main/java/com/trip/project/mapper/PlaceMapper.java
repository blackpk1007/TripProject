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
	
	@Select(" select * from place where placeCategory = 'restaurant' order by placeGood desc limit ${paging.getSkip()}, ${paging.getSize()}")
	List<PlaceDTO> placeSearchDefault(@Param("paging")placePagination paging);
	
	@Select(" select count(*) from place where placeName like '%${keyword}%' ")
	int placeSearchCount(@Param("keyword") String keyword);
	
	@Select(" SELECT l.userGender, recommandPlaceNumber, COUNT(*) AS count FROM login AS l JOIN recommand AS r ON l.userID = r.recommandUserID WHERE r.recommandPlaceNumber =#{recommandPlaceNumber} GROUP BY l.userGender ")
	List<LoginDTO> genderList(int recommandPlaceNumber);
	
	@Select(" SELECT DATE_FORMAT(l.userBirth, '%Y') AS userBirth, r.recommandPlaceNumber FROM login AS l JOIN recommand AS r ON l.userID = r.recommandUserID WHERE r.recommandPlaceNumber =#{recommandPlaceNumber} ")
	List<LoginDTO> birthList(int recommandPlaceNumber);
	
////	// 마이페이지에 recommand 전체 데이터 가져오기
//	@Select(" SELECT * FROM recommand WHERE recommandUserID=#{userID} " )
//	public List<RecommandDTO> usermainRecommand(String userID);	
	// 마이페이지에 recommand 2개이상 갯수 세는거
	@Select(" select p.placeName, r.recommandNowDate, r.recommandNumber from recommand r join place p on r.recommandPlaceNumber = p.placeNumber where r.recommandUserID = #{userID} group by recommandPlaceNumber having count(*) > 1 ")
	public List<RecommandDTO> user2recommand(String userID);
	// 마이페이지에 리뷰 부분에 recommandPlaceNumber로 place 정보 (placeName) 가져오는거, 리뷰쓴날자 가져오기 
	@Select("select p.placeName, r.recommandNowDate, r.recommandNumber from recommand r join place p on r.recommandPlaceNumber = p.placeNumber where r.recommandUserID = #{userID}")
	public List<RecommandDTO> placename(String userID);	
	
	// 마이페이지에 나의 리뷰 삭제하기 버튼
	@Delete(" delete from recommand where recommandUserID=#{userID} and recommandNumber=#{recommandNumber} ")
	int reviewdelete(String userID, String recommandNumber);

	@Insert(" insert into plan values(null, #{planName}, #{planFirstDate}, #{planLastDate}, default, #{shareID}, null) ")
	int planInsert(PlanDTO dto);
	
	@Insert(" insert into planDetail values(null, #{planName}, #{planDetailDate}, #{planDetailLon}, #{planDetailLat}, #{planDetailColor}, #{shareID}, null) ")
	int planDetailInsert(PlanDetailDTO dto);
	
	@Select(" select * from plan where shareID = #{shareID} ")
	PlanDTO userPlan(String shareID);
	
	//마이페이지 갯수
	@Select(" select * from plan where shareID = #{shareID} ")
	public List<PlanDTO> userPlancount(String shareID);
	
	@Select(" select * from planDetail where shareID = #{shareID} and planName = #{planName} ")
	List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto);
	
	// 마이페이지 삭제 - 플랜,플랜디테
	@Delete(" delete from plan where shareID = #{shareID} and planName = #{planName} ")
	int planDelete(String shareID, String planName);
	@Delete(" delete from planDetail where shareID = #{shareID} and planName = #{planName} ")
	int planDetaildelete(String shareID, String planName);
	// 코스, 코스디테일 삭제 
	@Delete(" delete from course where shareID = #{shareID} and planName = #{planName} ")
	int courseDelete(String shareID, String planName);
	@Delete(" delete from courseDetail where shareID = #{shareID} and planName = #{planName} ")
	int courseDetaildelete(String shareID, String planName);
	
	// 마이페이지 공유
	@Insert(" INSERT INTO course (courseFirstDate, courseLastDate, coursetravelDate, courseCount, shareID, planName )"
			+ "select planFirstDate, planLastDate, #{datecount}, planCount, shareID, planName "
			+ "from plan "
			+ "WHERE planName=#{planName} AND shareID=#{shareID} ")
	int planShare(String shareID, String planName, @Param("datecount")int datecount);
	@Insert(" INSERT INTO courseDetail (courseDetailDate, courseDetailLon, courseDetailLat, courseDetailColor, shareID, planName) "
			+ "select planDetailDate, planDetailLon, planDetailLat, planDetailColor, shareID, planName "
			+ "from planDetail "
			+ "WHERE planName=#{planName} AND  shareID=#{shareID} ")
	int planDetailshare(String shareID, String planName);
	@Select(" SELECT planFirstDate, planLastDate FROM plan WHERE planName=#{planName} AND shareID=#{shareID} ")
	PlanDTO datecount(String shareID, String planName);
}
