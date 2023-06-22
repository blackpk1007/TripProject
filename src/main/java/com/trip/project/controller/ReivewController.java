package com.trip.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trip.project.service.CommunityService;
import com.trip.project.service.Ocr;
import com.trip.project.service.OcrTest;

@Controller
@RequestMapping("/review")
public class ReivewController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityService cService;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private OcrTest OcrTest;
	@RequestMapping
	public String reviewmain() {
		
		return "mypagetest";
	}
	
	@PostMapping("/reveiwwrite")
    public String review(@RequestParam("attachFile") MultipartFile file, Model model, Integer placeNumber, HttpSession session) throws IOException {
		
       
		Ocr ocr = new Ocr(); 
        String res = ocr.ocr(file);
        JSONObject obj = new JSONObject(res);
        model.addAttribute("res", obj);
        model.addAttribute("num", OcrTest.selectByPlaceNumber(placeNumber));
        

        return "ocr";
    }
}
