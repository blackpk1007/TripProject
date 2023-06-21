package com.trip.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.project.dto.PlaceDTO;
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
	
	@ResponseBody
	@GetMapping("/fetchMarkers") 
	public List<PlaceDTO> planMarker(@RequestParam("category") String category) { 
		
		List<PlaceDTO> placeList = pservice.placeCategoryMarker(category);
		
		return placeList; 
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
