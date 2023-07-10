package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.MainpageDTO;
import com.trip.project.dto.PlaceDTO;

public interface MainpageService {
	public List<MainpageDTO> selectList();
	
	public List<MainpageDTO> animalList();
	
	public List<MainpageDTO> parentsList();
	
	PlaceDTO getPlaceInfo(String placeName);
	
	public CourseDTO courseCountMain1();
	
	public CourseDTO courseCountMain2();
	
	public CourseDTO courseCountMain3();
	
	public CourseDTO courseCountMain4();
}
