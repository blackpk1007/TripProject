package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.CourseDTO;

@Mapper
public interface CourseMapper {
	
	@Select(" select * from course order by courseFirstDate desc ")
	List<CourseDTO> courseDateDesc();
	
	@Select(" select * from course order by courseCount desc ")
	List<CourseDTO> courseCountDesc();
	
	@Select(" select * from course ")
	List<CourseDTO> courseList();
}
