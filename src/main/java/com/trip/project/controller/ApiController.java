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

@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ResourceLoader resourceLoader;
	
	
	@GetMapping("/ocr")
	public String ocrTest2(Model model) throws IOException {
		String path=Paths.get(resourceLoader.getResource("classpath:static").getURI()).toString();
		System.out.println(path);
		
		Ocr ocr =new Ocr();
		String res=ocr.ocr(path);
		
		JSONObject obj=new JSONObject(res);
		model.addAttribute("res",obj);
		
		return "ocr";
	}
}
