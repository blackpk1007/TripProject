package com.trip.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.trip.project.dto.LoginDTO;
import com.trip.project.service.CommunityService;
import com.trip.project.service.Ocr;
import com.trip.project.service.OcrTest;
import com.trip.project.service.PlanService;

@Controller
@RequestMapping("/review")
public class ReivewController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityService cService;
	
	@Autowired
	private PlanService pservice;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private OcrTest OcrTest;
	
	@ResponseBody
	@PostMapping("/reviewwrite")
    public List<Object> resultList(@RequestParam("attachFile") MultipartFile file, Model model, Integer placeNumber, HttpSession session) throws IOException {
		
       
		Ocr ocr = new Ocr();
        String res = ocr.ocr(file);
        JSONObject obj = new JSONObject(res);
        List<Object> resultList = new ArrayList<>();
        resultList.add(res);
        resultList.add(OcrTest.selectByPlaceNumber(placeNumber));
        resultList.add(OcrTest.selectRecommandPlaceNumber(placeNumber));
        return resultList;
    }

	
	@ResponseBody
	@PostMapping("/insertRecommandData")
	public void insertRecommandData(@RequestParam String userID, @RequestParam int placeNumber, @RequestParam String date, @RequestParam int db_check) {
	    OcrTest.insertRecommandData(userID, placeNumber, date);
	    if(db_check==2)OcrTest.updatePlaceGood(placeNumber);
	}
	
	// 마이페이지 나의 리뷰 recommand 삭제
	@ResponseBody
	@PostMapping("/reviewdelete")
	public String reviewdelete(@RequestParam("userID")String userID, @RequestParam("recommandNumber") String recommandNumber) {
		int res = pservice.reviewdelete(userID, recommandNumber);
		return userID.toString()+"님의"+recommandNumber.toString()+"번 리뷰가 삭제되었습니다.";
	}
	
	
}