package com.trip.project.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.dto.PlanDetailDTO;
import com.trip.project.dto.placePagination;
import com.trip.project.service.AirPlaneService;
import com.trip.project.service.PlanServiceImpl;
import com.trip.project.service.WeatherService;

@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private PlanServiceImpl pservice;
	
	@Autowired
	private AirPlaneService aservice;
	
	@Autowired
	private WeatherService wservice;
	
	@RequestMapping
	public String planMain(Model model, placePagination paging){
		System.out.println("controller main : "+paging);
		try {
			model.addAttribute("jeju", wservice.Jeju());
			model.addAttribute("seogwipo", wservice.Seogwipo());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		model.addAttribute("placeRestaurantList", pservice.placeRestaurantList(paging));
		model.addAttribute("placeListCount", pservice.placeRestaurantListCount());
		
		return "plan";
	}
	
	@ResponseBody
	@GetMapping("/genderList")
	public List<LoginDTO> genderList(@RequestParam int recommandPlaceNumber) {
		List<LoginDTO> genderList = pservice.genderList(recommandPlaceNumber);
		return genderList;
	}
	
	@ResponseBody
	@GetMapping("/birthList")
	public List<LoginDTO> birthList(@RequestParam int recommandPlaceNumber) {
		List<LoginDTO> birthList = pservice.birthList(recommandPlaceNumber);
		System.out.println(birthList);
		return birthList;
	}
	
	@ResponseBody
	@GetMapping("/fetchMarkers") 
	public Map<String, Object> planMarker(@RequestParam("category")String category, @RequestParam("pageNum") int pageNum, Model model) {
		int totalItems = pservice.placeCategoryCount(category);
		System.out.println("controller category : "+category);
		System.out.println("controller totalitem: "+totalItems);
        int totalPages = (int) Math.ceil((double) totalItems / 20); // 전체 페이지 수 계산

        // 현재 페이지 번호가 유효한 범위를 벗어날 경우 첫 번째 페이지로 설정
        if (pageNum < 1) {
            pageNum = 1;
        } else if (pageNum > totalPages) {
        	pageNum = totalPages;
        }
        placePagination paging = new placePagination(pageNum, 20);
        
        // 현재 페이지에 해당하는 데이터 조회
        List<PlaceDTO> placeList = pservice.placeCategoryMarker(category, paging);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("placeList", placeList);
        responseData.put("totalPages", totalPages);
        responseData.put("currentPage", pageNum);
		
		return responseData;
	}
	
	@ResponseBody
	@PostMapping("/createplan")
	public String createPlan(@RequestBody Map<String, Object> requestData, Model model) {
	    List<Map<String, Object>> inputValues = (List<Map<String, Object>>) requestData.get("inputValues");
	    String userID = (String) requestData.get("userID");
	    String planName = (String) requestData.get("planName");
	    String firstDate = null;
	    String lastDate = null;
		for (Map<String, Object> inputValue : inputValues) {
	        String date = (String) inputValue.get("date");
	        String color = (String) inputValue.get("color");
	        List<Map<String, String>> lonLatPairs = (List<Map<String, String>>) inputValue.get("lonLatPairs");
	     // 첫 번째 요소의 date 값을 가져오기
	        if (firstDate == null) {
	            firstDate = date;
	        }

	        // 매번 반복마다 마지막 요소의 date 값을 갱신하기
	        lastDate = date;
	        for (Map<String, String> lonLatPair : lonLatPairs) {
	            String lon = lonLatPair.get("lon");
	            String lat = lonLatPair.get("lat");
	            PlanDetailDTO detaildto = new PlanDetailDTO(null, userID, planName, date, lon, lat, color);	           
	            pservice.planDetailInsert(detaildto);
	            // 데이터베이스에 저장 로직 구현
	            // 예: saveDataToDatabase(userID, planName, date, color, lon, lat);
	        }
	        System.out.println("controller - user : "+userID);
	        System.out.println("controller - name : "+planName);
	        System.out.println("Controller - Date: " + date);
	        System.out.println("Controller - Color: " + color);
	        System.out.println("controller pairs : " + lonLatPairs + "\n");
	    }
		PlanDTO dto = new PlanDTO(null, userID, planName, firstDate, lastDate, 0);
		pservice.planInsert(dto);
		
		return "main"; 
	}
	
	@PostMapping("/course")
	public String course(@RequestBody List<Map<String, Object>> inputValues, Model model) {
	    model.addAttribute("inputValues", inputValues);

	    for (Map<String, Object> inputValue : inputValues) {
	        String date = (String) inputValue.get("date");
	        String color = (String) inputValue.get("color");
	        List<Map<String, String>> lonLatPairs = (List<Map<String, String>>) inputValue.get("lonLatPairs");

	        System.out.println("Controller - Date: " + date);
	        System.out.println("Controller - Color: " + color);
	        System.out.println("controller pairs : " + lonLatPairs + "\n");
	    }

	    return "course";
	}
	
	@RequestMapping("/airplane")
	public String airplane(Model model) throws IOException {
		
			model.addAttribute("arriveds", aservice.jejuArrived());
			model.addAttribute("boardings", aservice.jejuBoarding());
		
		return "airplane";
	}
	
	@ResponseBody
	@PostMapping("/search")
	public List<PlaceDTO> search(@RequestParam("keyword") String keyword, @RequestParam("pageNum") int pageNum) throws UnsupportedEncodingException {
		String KeywordDecode = URLDecoder.decode(keyword, "UTF-8");
		placePagination paging = new placePagination(pageNum, 20);

		List<PlaceDTO> dto = pservice.placeSearch(KeywordDecode, paging);

		return dto;
	}
	
}
