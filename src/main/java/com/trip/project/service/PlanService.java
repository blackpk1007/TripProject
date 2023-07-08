package com.trip.project.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.RecommandDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.dto.PlanDetailDTO;
import com.trip.project.dto.placePagination;

public interface PlanService {
	
	public List<PlaceDTO> placeCategoryMarker(String categoey, placePagination pageing);
	
	public int placeCategoryCount(String categoey);
	
	public List<PlaceDTO> placeRestaurantList(placePagination paging);
	
	public int placeRestaurantListCount();
	
	public List<PlaceDTO> placeSearch(String keyword, placePagination pageing);
	
	public int placeSearchCount(String keyword);
	
	public List<LoginDTO> genderList(int recommandPlaceNumber);
	
	public List<LoginDTO> birthList(int recommandPlaceNumber);
	
	// 마이페이지 리뷰 
	public List<RecommandDTO> usermainRecommand(String userID);
	public List<RecommandDTO> user2recommand(String userID);
	// 마이페이지 리뷰 삭제 버튼 
	public int reviewdelete(String userID, String recommandNumber);
	
	public int planInsert(PlanDTO dto);
	
	public int planDetailInsert(PlanDetailDTO dto);
	
	public PlanDTO userplan(String userID);
	
	public List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto);
	
	// 마이페이지 일정 삭제버튼, 플랜, 플랜디테일 
	public int planDelete(String userID, String planName);
	public int planDetaildelete(String userID, String planName);
	// 일정 삭제 - 코스, 코스 디테일
	public int courseDelete(String userID, String planName);
	public int courseDetaildelete(String userID, String planName);
	// 마이페이지 일정 공유버튼, 플랜,플랜디테일
	public int planShare(String userID, String planName);
	public int planDetailshare(String userID, String planName);
//	// 마이페이지 recommandPlaceNumber로 place 정보(placeName용) 가져오는거 
//	public List<PlaceDTO> userplace(String recommandPlaceNumber);
	
	public List<PlanDTO> userPlancount(String userID);
}
