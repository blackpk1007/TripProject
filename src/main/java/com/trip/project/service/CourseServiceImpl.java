package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.CourseDetailDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.mapper.CourseMapper;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper cmapper;
	
	@Override
	public List<CourseDTO> courseList() {
		
		return cmapper.courseList();
	}

	@Override
	public List<CourseDTO> courseDateSort() {

		return cmapper.courseDateSort();
	}

	@Override
	public List<CourseDTO> courseCountSort() {

		return cmapper.courseCountSort();
	}

	@Override
	public List<CourseDTO> courseseason(List<Integer> months) {

		return cmapper.courseseason(months);
	}

	@Override
	public List<CourseDTO> coursedays(List<Integer> days) {

		return cmapper.coursedays(days);
	}

	@Override
	public List<CourseDTO> courseDaysSeasons(List<Integer> days, List<Integer> months) {
		
		return cmapper.courseDaysSeasons(days, months);
	}

	@Override
	public List<CourseDetailDTO> courseDetailList(String userID, String planName) {

		return cmapper.courseDetailList(userID, planName);
	}

	@Override
	public int courseListCount(String userID, String planName) {
		
		return cmapper.courseListCount(userID, planName);
	}

	@Override
	public CourseDTO courseDetail(String userID, String planName) {

		return cmapper.courseDetail(userID, planName);
	}

	@Override
	public List<PlaceDTO> coursePlace(String userID, String planName) {

		return cmapper.coursePlace(userID, planName);
	}

	@Override
	public List<PlaceDTO> courseImage(String userID, String planName) {

		return cmapper.courseImage(userID, planName);
	}

}
