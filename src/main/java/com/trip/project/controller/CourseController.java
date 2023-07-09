	package com.trip.project.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.CourseDetailDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.PlanDetailDTO;
import com.trip.project.service.CourseService;
import com.trip.project.service.MainpageService;
import com.trip.project.service.PlanService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService cService;
	
	@Autowired
	private MainpageService mservice;
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
	public String coursedetail(Model model, String planName, String shareID){
		cService.courseListCount(shareID, planName);
		List<CourseDetailDTO> dtoList = cService.courseDetailList(shareID, planName);
		List<Map<String, Object>> resultList = new ArrayList<>();
		   Map<String, Object> resultMap = new LinkedHashMap<>();

		   for (CourseDetailDTO dto : dtoList) {
		        String date = dto.getCourseDetailDate();
		        String color = dto.getCourseDetailColor();
		        
		        if (!resultMap.containsKey(date)) {
		            Map<String, Object> itemMap = new HashMap<>();
		            itemMap.put("date", date);
		            itemMap.put("color", color);
		            itemMap.put("lonLatPairs", new ArrayList<>());
		            itemMap.put("placeName", new ArrayList<>());
		            resultMap.put(date, itemMap);
		        }
		        
		        Map<String, Object> itemMap = (Map<String, Object>) resultMap.get(date);
		        List<Map<String, String>> lonLatPairs = (List<Map<String, String>>) itemMap.get("lonLatPairs");
		        List<Map<String, String>> placeName = (List<Map<String, String>>) itemMap.get("placeName");
		        Map<String, String> lonLatMap = new HashMap<>();
		        Map<String, String> placeNameMap = new HashMap<>();
		        placeNameMap.put("placeName", dto.getPlaceName());
		        lonLatMap.put("lon", dto.getCourseDetailLon());
		        lonLatMap.put("lat", dto.getCourseDetailLat());
		        lonLatPairs.add(lonLatMap);
		        placeName.add(placeNameMap);
		    }
		   resultList.add(resultMap);
		   
		   model.addAttribute("coursemarker", resultList);
		   model.addAttribute("coursedetailLists", dtoList);
		   model.addAttribute("coursedetail", cService.courseDetail(shareID, planName));
		   model.addAttribute("courseimage", cService.courseImage(shareID, planName));
		  
		return "coursedetail";
		}
	
	@ResponseBody
	@PostMapping("/info")
	public PlaceDTO placeInfo(@RequestParam("placeName") String placeName) {
		PlaceDTO dto = mservice.getPlaceInfo(placeName); 
		
		return dto;
	}
}
