package com.trip.project.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.RecommandDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.dto.PlanDetailDTO;

public interface PlanService {
	
	public List<PlaceDTO> placeCategoryMarker(String categoey);
	
	public List<PlaceDTO> placeRestaurantList();
	
	public List<PlaceDTO> placeSearch(String keyword);

	public List<LoginDTO> genderList(int recommandPlaceNumber);
	
	public List<LoginDTO> birthList(int recommandPlaceNumber);
	
	public List<RecommandDTO> usermainRecommand(String userID);

	public int planInsert(PlanDTO dto);
	
	public int planDetailInsert(PlanDetailDTO dto);
	
	public PlanDTO userplan(String userID);
	
	public List<PlanDetailDTO> userPlanDetail(PlanDetailDTO dto);
	
	public int planDelete(PlanDTO dto);
}
