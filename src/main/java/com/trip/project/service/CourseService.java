package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.CourseDetailDTO;

public interface CourseService {
	
	List<CourseDTO> courseList();
	
	List<CourseDTO> courseDateSort();
	
	List<CourseDTO> courseCountSort();
	
	List<CourseDTO> courseseason(List<Integer> months);
	
	List<CourseDTO> coursedays(List<Integer> days);
	
	List<CourseDTO> courseDaysSeasons(List<Integer> days, List<Integer> months);
	
	List<CourseDetailDTO> courseDetailList(String userID, String planName);
	
	int courseListCount(String userID, String planName);
}
