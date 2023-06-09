package com.trip.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.project.dto.PlaceDTO;
import com.trip.project.service.PlanServiceImpl;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	private PlanServiceImpl pservice;
	
	
	@RequestMapping
	public String planMain(Model model) {
		
		model.addAttribute("placelist", pservice.placeList());
		
		return "plan";
	}
	
	@RequestMapping("/marker")
	public String planMarker(Model model) {
//		List<PlaceDTO> placeList = new ArrayList<PlaceDTO>();
		
		
		System.out.println("place : "+pservice.placeList());
		
		return "plan";
	}
}
