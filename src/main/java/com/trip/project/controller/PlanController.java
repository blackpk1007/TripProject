package com.trip.project.controller;

import java.nio.charset.Charset;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.service.PlanServiceImpl;

@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private PlanServiceImpl pservice;
	
	@RequestMapping
	public String planMain(Model model){

		model.addAttribute("placeRestaurantList", pservice.placeRestaurantList());

		return "plan";
	}
	
//	@ResponseBody
//	@GetMapping("/fetchPlaceList")
//	public Map<String, List> planMarker() {
//		List<PlaceDTO> placeList = pservice.placeList();
////		List<LoginDTO> genderList = pservice.genderList();
//		
//		Map<String,List> map = new HashMap<String, List>();
//		map.put("placeList", placeList);
////		map.put("genderList", genderList);
//		
//		return map;
//	}
  
	@ResponseBody
	@GetMapping("/genderList")
	public List<LoginDTO> genderList(@RequestParam int recommandPlaceNumber) {
		List<LoginDTO> genderList = pservice.genderList(recommandPlaceNumber);
		return genderList;
	}
	
	@ResponseBody
	@GetMapping("/birthList")
	public List<LoginDTO> birthList(@RequestParam int recommandPlaceNumber) {
		List<LoginDTO> birthList = pservice.birthList(recommandPlaceNumber);
		System.out.println(birthList);
		return birthList;
	}
	
	@ResponseBody
	@GetMapping("/fetchMarkers") 
	public List<PlaceDTO> planMarker(@RequestParam("category") String category) { 
		
		List<PlaceDTO> placeList = pservice.placeCategoryMarker(category);
		
		return placeList; 
	}
	
	@PostMapping("/createplan")
	public String createPlan(PlanDTO plandto) {
		
		System.out.println("controller : "+plandto);
		return "main"; 
	}
	
	@GetMapping("/course")
	public String course(@RequestParam("date") String date, @RequestParam("lon") String Lon, @RequestParam("lat") String Lat) {
		System.out.println("controller : "+date);
		System.out.println("controller : "+Lon);
		System.out.println("controller : "+Lat);
		return "course";
	}
//	@ResponseBody
//	@GetMapping("/fetchMarkers")
//	public String planMarker(@RequestParam String category, Model model){
//		System.out.println("category 들어옴?" +category); 
//		List<PlaceDTO> categoryList = pservice.placeCategoryMarker(category);
//
//		model.addAttribute("placeCategoryList", categoryList);
//		System.out.println("list 나감?"+categoryList);
//		return "plan";
//	}
}
