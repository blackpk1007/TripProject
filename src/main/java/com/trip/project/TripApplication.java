package com.trip.project;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.project.dto.PlaceDTO;
import com.trip.project.service.AirPlaneService;
import com.trip.project.service.CrawlingService;
import com.trip.project.service.MainpageService;

@CrossOrigin(origins = "http://localhost:8787")
@Controller
@SpringBootApplication
public class TripApplication {
	
	@Autowired
	private CrawlingService cservice;
	
	@Autowired
	private AirPlaneService aservice;
	
	@Autowired
	private MainpageService mainpageService;
	
	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}
	
	// 메인 페이지
	@RequestMapping("/")
	public String main(Model model) throws IOException {
		
		/*
		 * model.addAttribute("arriveds", aservice.jejuArrived());
		 * model.addAttribute("boardings", aservice.jejuBoarding());
		 */
		model.addAttribute("list", mainpageService.selectList());
		model.addAttribute("animalList", mainpageService.animalList());
		model.addAttribute("parentsList", mainpageService.parentsList());
		return "main";
	}
	
	@GetMapping("/main/getPlaceInfo")
    @ResponseBody
    public PlaceDTO getPlaceInfo(@RequestParam("placeName") String placeName) {
        return mainpageService.getPlaceInfo(placeName);
    }
}
