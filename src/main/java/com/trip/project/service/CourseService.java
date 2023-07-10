package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.CourseDetailDTO;
import com.trip.project.dto.PlaceDTO;

public interface CourseService {
	
	List<CourseDTO> courseList();
	
	CourseDTO courseDetail(String shareID, String planName);
	
	List<CourseDTO> courseDateSort();
	
	List<CourseDTO> courseCountSort();
	
	List<PlaceDTO> courseImage(String userID, String planName);
	
	List<CourseDTO> courseseason(List<Integer> months);
	
	List<CourseDTO> coursedays(List<Integer> days);
	
	List<CourseDTO> courseDaysSeasons(List<Integer> days, List<Integer> months);
	
	List<CourseDetailDTO> courseDetailList(String shareID, String planName);
	
	//List<PlaceDTO> coursePlace(String userID, String planName);
	
	int courseListCount(String shareID, String planName);
	
	int coursesave(String shareID, String planName, String saveID);
	
	int courseDetailSave(String shareID, String planName, String saveID);
	
	List<CourseDTO> travelDate(String shareID);
	
	List<CourseDTO> travelsaveDate(String shareID);
}
