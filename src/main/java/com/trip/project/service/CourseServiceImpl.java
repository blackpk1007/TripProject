package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.CourseDTO;
import com.trip.project.mapper.CourseMapper;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper cmapper;
	
	@Override
	public List<CourseDTO> courseList() {
		
		return cmapper.courseList();
	}

}
