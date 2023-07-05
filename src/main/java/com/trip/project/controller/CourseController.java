package com.trip.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<CourseDTO> dto = null;
		
		if(sort.equals("popularity")) {
			dto = cService.courseCountSort();
		}else {			
			dto = cService.courseDateSort();
		}
		
		return dto;
	}
	
	@ResponseBody
	@PostMapping("/season")
	public List<CourseDTO> season(@RequestBody Map<String, List<Integer>> months){
		List<Integer> selectMonths = months.get("months");
		List<CourseDTO> season = cService.courseseason(selectMonths);
		
		return season;
	}
	
	@ResponseBody
	@PostMapping("/days")
	public List<CourseDTO> days(@RequestBody Map<String, List<Integer>> days){
		List<Integer> selectDays = days.get("days");
		List<CourseDTO> season = cService.coursedays(selectDays);
		
		return season;
	}
	@ResponseBody
	@PostMapping("/seasonsdates")
	public List<CourseDTO> seasonsdates(@RequestBody Map<String, List<Integer>> days){
		List<Integer> selectDays = days.get("days");
		List<Integer> selectMonths = days.get("months");
		System.out.println("season days  days: "+selectDays);
		System.out.println("season days  months: "+selectMonths);
		List<CourseDTO> season = cService.courseDaysSeasons(selectDays, selectMonths);
		
		return season;
	}
	
	@ResponseBody
	@PostMapping("/all")
	public List<CourseDTO> all(){
		
		List<CourseDTO> all = cService.courseList();
		
		return all;
	}
	// 코스 상세
	@RequestMapping("/coursedetail")
	public String coursedetail(){
		return "coursedetail";
	}
}
