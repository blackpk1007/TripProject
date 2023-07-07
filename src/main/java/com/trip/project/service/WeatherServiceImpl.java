package com.trip.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.trip.project.dto.WeatherDTO;

@Service
public class WeatherServiceImpl implements WeatherService{

	@Override
	public WeatherDTO Jeju() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HH00");
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String baseDate = dateFormat.format(cal.getTime()); // 현재 날짜를 base_date에 설정
		String baseTime = timeFormat.format(cal.getTime()); // 현재 시간을 base_time에 설정
		String previousDate = dateFormat.format(cal2.getTime()); // 현재 날짜를 base_date에 설정
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=1kainzqQnmN7SXsZINtwO%2FxHhS9Vym1t4CDA1aTLEbOtE%2BInS7RhlsMw%2F4vTuOAaepTP5Cv8gt1sge%2Bhc%2BDJtA%3D%3D"); /*
																																 * Service
																																 * Key
																																 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("550", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
				+ URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
				+ URLEncoder.encode(previousDate, "UTF-8")); /* ‘21년 6월 28일발표 */
		urlBuilder.append(
				"&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /* 05시 발표 */
		urlBuilder.append(
				"&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("53", "UTF-8")); /* 예보지점의 X 좌표값 */
		urlBuilder.append(
				"&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("38", "UTF-8")); /* 예보지점의 Y 좌표값 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
	
		 // JSON 데이터 파싱 및 필요한 정보 추출하여 DTO에 담기
		try {
	    JSONObject json = new JSONObject(sb.toString());
	    JSONObject response = json.getJSONObject("response");
	    if (!response.has("body")) {
			return null;
		}
	    JSONObject body = response.getJSONObject("body");
	    JSONObject items = body.getJSONObject("items");
	    JSONArray weatherArray = items.getJSONArray("item");
	    
	    int minimumtemp = 0;
	    int highesttemp = 0;
	    int currenttemp = 0;
	    int sky = 0;
	    int pty = 0;
	    String day = null;
	    for (int i = 0; i < weatherArray.length(); i++) {
	        JSONObject weatherObj = weatherArray.getJSONObject(i);
	        String fcsttime = weatherObj.getString("fcstTime");
	        String fcstdate = weatherObj.getString("fcstDate");
	        String bDate = weatherObj.getString("baseDate");
	        // 현재 시간과 base_time이 일치하는 경우에만 처리
	        String category = weatherObj.getString("category");
	        String fcstValue = weatherObj.getString("fcstValue");
	        
	        if (baseTime.compareTo("0600") >= 0 && baseTime.compareTo("1800") <= 0) {
	        	day = "day";
	        }else {
	        	day = "night";
	        }
	        if (baseTime.equals(fcsttime) && baseDate.equals(fcstdate)) {
	        	
	        	 if ("TMP".equals(category)) {
	        		currenttemp = Integer.parseInt(fcstValue);
	        	}else if ("SKY".equals(category)) {
	        		sky = Integer.parseInt(fcstValue);
	        	}else if ("PTY".equals(category)) {
	        		pty = Integer.parseInt(fcstValue);
	        	}
	        }
	        
	        if("1500".equals(fcsttime) && baseDate.equals(fcstdate)) {
	        	if ("TMX".equals(category)) {
	        		String[] fcstValue2 = fcstValue.split("\\.");

	        		String result = fcstValue2[0];
	        		highesttemp = (int)Integer.parseInt(result);
	        	}
	        }
	        
	        if(previousDate.equals(bDate) && "0600".equals(fcsttime)) {
	        	if(baseDate.equals(fcstdate)) {
	        		if ("TMN".equals(category)) {
	        			String[] fcstValue2 = fcstValue.split("\\.");

		        		String result = fcstValue2[0];
		        	    minimumtemp = (int)Integer.parseInt(result);
	        		}
	        	}
	        }
	    }
	    WeatherDTO weatherDTO = new WeatherDTO(day, minimumtemp, highesttemp, currenttemp, sky, pty);
		return weatherDTO;
		} catch (JSONException e) {
			e.printStackTrace();
            return null;
        }
	}

	@Override
	public WeatherDTO Seogwipo() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HH00");
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String baseDate = dateFormat.format(cal.getTime()); // 현재 날짜를 base_date에 설정
		String baseTime = timeFormat.format(cal.getTime()); // 현재 시간을 base_time에 설정
		String previousDate = dateFormat.format(cal2.getTime()); // 현재 날짜를 base_date에 설정
		
		System.out.println("weather service baseDate : "+baseDate);
        System.out.println("weather service baseTime : "+baseTime);
        
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=1kainzqQnmN7SXsZINtwO%2FxHhS9Vym1t4CDA1aTLEbOtE%2BInS7RhlsMw%2F4vTuOAaepTP5Cv8gt1sge%2Bhc%2BDJtA%3D%3D"); /*
																																 * Service
																																 * Key
																																 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("550", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
				+ URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
				+ URLEncoder.encode(previousDate, "UTF-8")); /* ‘21년 6월 28일발표 */
		urlBuilder.append(
				"&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /* 05시 발표 */
		urlBuilder.append(
				"&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("52", "UTF-8")); /* 예보지점의 X 좌표값 */
		urlBuilder.append(
				"&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("33", "UTF-8")); /* 예보지점의 Y 좌표값 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
		
		 // JSON 데이터 파싱 및 필요한 정보 추출하여 DTO에 담기
		try {
	    JSONObject json = new JSONObject(sb.toString());
	    JSONObject response = json.getJSONObject("response");
	    if (!response.has("body")) {
			return null;
		}
	    JSONObject body = response.getJSONObject("body");
	    JSONObject items = body.getJSONObject("items");
	    JSONArray weatherArray = items.getJSONArray("item");
	    
	    int minimumtemp = 0;
	    int highesttemp = 0;
	    int currenttemp = 0;
	    int sky = 0;
	    int pty = 0;
	    String day = null;
	    
	    for (int i = 0; i < weatherArray.length(); i++) {
	        JSONObject weatherObj = weatherArray.getJSONObject(i);
	        String fcsttime = weatherObj.getString("fcstTime");
	        String fcstdate = weatherObj.getString("fcstDate");
	        String bDate = weatherObj.getString("baseDate");
	        // 현재 시간과 base_time이 일치하는 경우에만 처리
	        String category = weatherObj.getString("category");
	        String fcstValue = weatherObj.getString("fcstValue");
	        if (baseTime.compareTo("0600") >= 0 && baseTime.compareTo("1800") <= 0) {
	        	day = "day";
	        }else {
	        	day = "night";
	        }
	        
	        if (baseTime.equals(fcsttime) && baseDate.equals(fcstdate)) {
	        	
	        	 if ("TMP".equals(category)) {
	        		currenttemp = Integer.parseInt(fcstValue);
	        	}else if ("SKY".equals(category)) {
	        		sky = Integer.parseInt(fcstValue);
	        	}else if ("PTY".equals(category)) {
	        		pty = Integer.parseInt(fcstValue);
	        	}
	        }
	        
	        if("1500".equals(fcsttime) && baseDate.equals(fcstdate)) {
	        	if ("TMX".equals(category)) {
	        		String[] fcstValue2 = fcstValue.split("\\.");

	        		String result = fcstValue2[0];
	        		highesttemp = Integer.parseInt(result);
	        	}
	        }
	        
	        if(previousDate.equals(bDate) && "0600".equals(fcsttime)) {
	        	if(baseDate.equals(fcstdate)) {
	        		if ("TMN".equals(category)) {
		        		String[] fcstValue2 = fcstValue.split("\\.");

		        		String result = fcstValue2[0];
		        	    minimumtemp = Integer.parseInt(result);
	        		}
	        	}
	        }
	    }
	    WeatherDTO weatherDTO = new WeatherDTO(day, minimumtemp, highesttemp, currenttemp, sky, pty);
		return weatherDTO;
	}
		catch (JSONException e) {
		e.printStackTrace();
        return null;
    }
}
	
}
