package com.trip.project.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("/airplane")
public class AirPlaneController {
	
	
	@RequestMapping
	public static Map<String, Object> FlightOpratInfoApi() throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

	    try {
	        StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "발급받은 API 키를 넣어주세요"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("depAirportId","UTF-8") + "=" + URLEncoder.encode("NAARKJJ", "UTF-8")); /*출발공항ID*/
	        urlBuilder.append("&" + URLEncoder.encode("arrAirportId","UTF-8") + "=" + URLEncoder.encode("NAARKPC", "UTF-8")); /*도착공항ID*/
	        urlBuilder.append("&" + URLEncoder.encode("depPlandTime","UTF-8") + "=" + URLEncoder.encode("20201201", "UTF-8")); /*출발일*/
	        urlBuilder.append("&" + URLEncoder.encode("airlineId","UTF-8") + "=" + URLEncoder.encode("AAR", "UTF-8")); /*항공사ID*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	    	rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } else {
	    	rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	    	sb.append(line);
	    }
	    rd.close();
	    conn.disconnect();

	    org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
	    String xmlJSONObjString = xmlJSONObj.toString();
	    System.out.println("### xmlJSONObjString=>"+xmlJSONObjString);

	    ObjectMapper objectMapper = new ObjectMapper();
	    Map<String, Object> map = new HashMap<>();
	    map = objectMapper.readValue(xmlJSONObjString, new TypeReference <Map<String,Object>>(){});
	    Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
	    Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
	    Map<String, Object> items = null;
	    List<Map<String, Object>> itemList = null;

	    items = (Map<String, Object>) body.get("items");
	    itemList = (List<Map<String, Object>>) items.get("item");

	    System.out.println("### map="+map);
	    System.out.println("### dataResponse="+dataResponse);
	    System.out.println("### body="+body);
	    System.out.println("### items="+items);
	    System.out.println("### itemList="+itemList);

	    resultMap.put("Result", "0000");
	    resultMap.put("numOfRows", body.get("numOfRows"));
	    resultMap.put("pageNo", body.get("pageNo"));
	    resultMap.put("totalCount", body.get("totalCount"));
	    resultMap.put("data", itemList);

	    } catch (Exception e){
	        e.printStackTrace();
	        resultMap.clear();
	        resultMap.put("Result", "0001");
	    }

	    return resultMap;
	}
}
