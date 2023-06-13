package com.trip.project;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.project.service.AirPlaneService;
import com.trip.project.service.CrawlingService;

@CrossOrigin(origins = "http://localhost:8787")
@Controller
@SpringBootApplication
public class TripApplication {
	
	@Autowired
	private CrawlingService cservice;
	
	@Autowired
	private AirPlaneService aservice;
	
	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}
	
	// 메인 페이지
	@RequestMapping("/")
	public String main(Model model) throws IOException {
		
		model.addAttribute("arriveds", aservice.jejuArrived());
		model.addAttribute("boardings", aservice.jejuBoarding());
		
		return "main";
	}
	
	// 크롤링 테스트 페이지
	@RequestMapping("/crawling")
	public void crawling() {
		
		cservice.crawinsert();
	}
}
