package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.dto.PlanDetailDTO;
import com.trip.project.mapper.PlaceMapper;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlaceMapper pmapper;
	
	@Override
	public List<PlaceDTO> placeCategoryMarker(String categoey) {
		
		return pmapper.placeCategoryMarker(categoey);
	}

	@Override
	public List<PlaceDTO> placeRestaurantList() {

		return pmapper.placeRestaurantList();
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
	public List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto) {

		return pmapper.userPlanDetail(dto);
	}

	@Override
	public int planDelete(PlanDTO dto) {

		return pmapper.planDelete(dto);
	}

	@Override
	public List<PlaceDTO> placeSearch(String keyword) {

		return pmapper.placeSearch(keyword);
	}


}
