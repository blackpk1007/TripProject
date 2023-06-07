package com.trip.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.project.dto.PlaceDTO;
import com.trip.project.service.CrawlingService;

@Controller
@SpringBootApplication
public class TripApplication {
	
	@Autowired
	private CrawlingService cservice;
	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}
	
	// 메인 페이지
	@RequestMapping("/")
	public String main() {
		
		return "main";
	}
	
	// 크롤링 테스트 페이지
	@RequestMapping("/crawling")
	public void crawling() {
		
		cservice.crawinsert();
	}
}
