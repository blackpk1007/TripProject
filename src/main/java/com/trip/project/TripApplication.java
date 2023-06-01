package com.trip.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class TripApplication {

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
	public String crawling() {
		
		return "crawling";
	}
	
}
