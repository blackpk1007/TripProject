package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.RecommandDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.dto.PlanDetailDTO;
import com.trip.project.dto.placePagination;
import com.trip.project.mapper.PlaceMapper;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlaceMapper pmapper;
	
	@Override
	public List<PlaceDTO> placeCategoryMarker(String categoey, placePagination paging) {
		
		return pmapper.placeCategoryMarker(categoey, paging);
	}

	@Override
	public List<PlaceDTO> placeRestaurantList(placePagination paging) {

		return pmapper.placeRestaurantList(paging);
	}

	@Override
	public List<LoginDTO> genderList(int recommandPlaceNumber) {
		
		return pmapper.genderList(recommandPlaceNumber);
	}
	
	@Override
	public List<LoginDTO> birthList(int recommandPlaceNumber) {
		
		return pmapper.birthList(recommandPlaceNumber);
	}
	public int planInsert(PlanDTO dto) {

		return pmapper.planInsert(dto);
	}

	@Override
	public int planDetailInsert(PlanDetailDTO dto) {

		return pmapper.planDetailInsert(dto);
	}

	@Override
	public PlanDTO userplan(String shareID) {

		return pmapper.userPlan(shareID);
	}
	
	@Override
	public List<PlanDTO> userPlancount(String shareID) {

		return pmapper.userPlancount(shareID);
	}

	@Override
	public List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto) {

		return pmapper.userPlanDetail(dto);
	}
	// 마이페이지 나의 일정 삭제 - 플랜,플랜디테
	@Override
	public int planDelete(String shareID, String planName) {

		return pmapper.planDelete(shareID,planName);
	}
	@Override
	public int planDetaildelete(String shareID, String planName) {
		
		return pmapper.planDetaildelete(shareID, planName);
	}
	// 코스,코스디테일 삭제 
	@Override
	public int courseDelete(String shareID, String planName) {
		return pmapper.courseDelete(shareID, planName);
	}
	
	@Override
	public int courseDetaildelete(String shareID, String planName) {
		return pmapper.courseDetaildelete(shareID, planName);
	}
	

	@Override
	public List<PlaceDTO> placeSearch(String keyword, placePagination paging) {

		return pmapper.placeSearch(keyword, paging);
	}

	@Override
	public int placeRestaurantListCount() {

		return pmapper.placeRestaurantListCount();
	}

	@Override
	public int placeCategoryCount(String categoey) {

		return pmapper.placeCategoryCount(categoey);
	}

	@Override
	public int planShare(String shareID, String planName) {
		PlanDTO dto = pmapper.datecount(shareID, planName);
		int fd = Integer.parseInt(dto.getPlanFirstDate().replaceAll("-", ""));
		int ld = Integer.parseInt(dto.getPlanLastDate().replaceAll("-", ""));
		int datecount = ld-fd+1;
		
		return pmapper.planShare(shareID, planName, datecount);
	}
	@Override
	public int planDetailshare(String shareID, String planName) {
		
		return pmapper.planDetailshare(shareID, planName);
	}
//	// 마이페이지 recommandPlaceNumber로 place 정보(placeName용) 가져오는거 
//	@Override
//	public List<PlaceDTO> userplace(String recommandPlaceNumber) {
//		
//		return pmapper.userplace(recommandPlaceNumber);
//	}

	// 마이페이지 리뷰 갯수
//	@Override
//	public List<RecommandDTO> usermainRecommand(String userID) {
//		
//		return pmapper.usermainRecommand(userID);
//	}
	@Override
	public List<RecommandDTO> user2recommand(String userID) {

		return pmapper.user2recommand(userID);
	}


	@Override
	public int placeSearchCount(String keyword) {

		return pmapper.placeSearchCount(keyword);
}
	// 마이페이지 리뷰 삭제 recommand 
	@Override
	public int reviewdelete(String userID, String recommandNumber) {

		return pmapper.reviewdelete(userID, recommandNumber);
	}

	@Override
	public List<RecommandDTO> placename(String userID) {
		
		return pmapper.placename(userID);
	}

	@Override
	public List<PlaceDTO> placeSearchDefault(placePagination paging) {

		return pmapper.placeSearchDefault(paging);
	}

	
}
