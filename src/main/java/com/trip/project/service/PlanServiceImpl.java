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

	@Override
	public List<RecommandDTO> usermainRecommand(String userID) {
		return pmapper.usermainRecommand(userID);
}
	public int planInsert(PlanDTO dto) {

		return pmapper.planInsert(dto);
	}

	@Override
	public int planDetailInsert(PlanDetailDTO dto) {

		return pmapper.planDetailInsert(dto);
	}

	@Override
	public PlanDTO userplan(String userID) {

		return pmapper.userPlan(userID);
	}
	
	@Override
	public List<PlanDTO> userPlancount(String userID) {

		return pmapper.userPlancount(userID);
	}

	@Override
	public List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto) {

		return pmapper.userPlanDetail(dto);
	}

	@Override
	public int planDelete(String userID, String planName) {

		return pmapper.planDelete(userID,planName);
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


}
