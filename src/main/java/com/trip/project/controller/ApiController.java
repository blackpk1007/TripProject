package com.trip.project.controller;

import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.project.service.Ocr;
import com.trip.project.service.OcrTest;

@Controller
@RequestMapping("/ocr")
public class ApiController {

	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private OcrTest OcrTest;
	
	@RequestMapping
	public String ocrTest2(Model model) throws IOException {
		String path=Paths.get(resourceLoader.getResource("classpath:static").getURI()).toString();
		System.out.println(path);
		
		Ocr ocr =new Ocr();
//		String res=ocr.ocr(path);
//		
//		JSONObject obj=new JSONObject(res);
//		
//		model.addAttribute("res",obj);
		model.addAttribute("num",OcrTest.selectOne());
		
		return "ocr";
	}
	
	@GetMapping("/weather")
	public String weather() {
		
		return "";
	}
}
