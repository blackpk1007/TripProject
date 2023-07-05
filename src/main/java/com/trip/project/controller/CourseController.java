package com.trip.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.project.dto.CourseDTO;
import com.trip.project.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService cService;
	
	// 코스 추천
	@RequestMapping("/recommandcourse")
	public String recommandcourse(Model model) {
		
		model.addAttribute("courses", cService.courseList());
		
		return "recommandcourse";
	}
	
	@ResponseBody
	@PostMapping("/sort")
	public List<CourseDTO> sort(@RequestParam("sort") String sort){
		
		
		return null;
	}
	

	// 코스 상세
	@RequestMapping("/coursedetail")
	public String coursedetail(){
		return "coursedetail";
	}
}
