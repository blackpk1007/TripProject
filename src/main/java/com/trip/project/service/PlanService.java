package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.PlaceDTO;

public interface PlanService {
	
	public List<PlaceDTO> placeCategoryMarker(String categoey);
	
	public List<PlaceDTO> placeRestaurantList();

}
