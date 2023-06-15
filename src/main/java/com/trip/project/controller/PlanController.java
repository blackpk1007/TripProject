package com.trip.project.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.service.PlanServiceImpl;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	private PlanServiceImpl pservice;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping
	public String planMain(Model model) throws JsonProcessingException {

		model.addAttribute("placelist", pservice.placeList());
		

		return "plan";

	}
	
	@ResponseBody
	@GetMapping("/fetchPlaceList")
	public List<PlaceDTO> planMarker() {
		List<PlaceDTO> placeList = pservice.placeList();
		
		return placeList;
	}
}
