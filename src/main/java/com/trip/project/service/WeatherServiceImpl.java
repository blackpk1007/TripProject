package com.trip.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.trip.project.dto.WeatherDTO;

@Service
public class WeatherServiceImpl implements WeatherService{

	@Override
	public List<WeatherDTO> Jeju() throws IOException {
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
				+ URLEncoder.encode("10000", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
				+ URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
				+ URLEncoder.encode("20230703", "UTF-8")); /* ‘21년 6월 28일발표 */
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
		System.out.println(sb.toString());
		
		 // JSON 데이터 파싱 및 필요한 정보 추출하여 DTO에 담기
	    JSONObject json = new JSONObject(sb.toString());
	    JSONObject response = json.getJSONObject("response");
	    JSONObject body = response.getJSONObject("body");
	    JSONObject items = body.getJSONObject("items");
	    JSONArray weatherArray = items.getJSONArray("item");

	    Calendar cal = Calendar.getInstance();
	    Date currentTime = cal.getTime();
	    
	    List<WeatherDTO> weatherList = new ArrayList<>();
	    
	    for (int i = 0; i < weatherArray.length(); i++) {
	        JSONObject weatherObj = weatherArray.getJSONObject(i);

	        String baseDate = weatherObj.getString("baseDate");
	        String baseTime = weatherObj.getString("baseTime");

	        // 현재 시간과 base_time이 일치하는 경우에만 처리
	        if (isCurrentTimeMatchBaseTime(currentTime, baseDate, baseTime)) {
	            String category = weatherObj.getString("category");
	            String minimumtemp = weatherObj.getString("fcstValue");
	            String highesttemp = weatherObj.getString("fcstValue");
	            String currenttemp = weatherObj.getString("fcstValue");
	            String sky = weatherObj.getString("fcstValue");
	            String pty = weatherObj.getString("fcstValue");

	            WeatherDTO weatherDTO = new WeatherDTO(category, minimumtemp, highesttemp, currenttemp, sky, pty);
	            weatherList.add(weatherDTO);
	        }
	        System.out.println("service weather List : "+weatherList);
	    }
		return weatherList;
	}

	@Override
	public List<WeatherDTO> Seogwipo() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HH00");
		Calendar cal = Calendar.getInstance();
	    Date currentTime = cal.getTime();
	    
		String baseDate = dateFormat.format(cal.getTime()); // 현재 날짜를 base_date에 설정
		String baseTime = timeFormat.format(cal.getTime()); // 현재 시간을 base_time에 설정
		
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
				+ URLEncoder.encode("10000", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
				+ URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
				+ URLEncoder.encode(baseDate, "UTF-8")); /* ‘21년 6월 28일발표 */
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
		System.out.println(sb.toString());
	
		 // JSON 데이터 파싱 및 필요한 정보 추출하여 DTO에 담기
	    JSONObject json = new JSONObject(sb.toString());
	    JSONObject response = json.getJSONObject("response");
	    JSONObject body = response.getJSONObject("body");
	    JSONObject items = body.getJSONObject("items");
	    JSONArray weatherArray = items.getJSONArray("item");

	    List<WeatherDTO> weatherList = new ArrayList<>();
	    
	    for (int i = 0; i < weatherArray.length(); i++) {
	        JSONObject weatherObj = weatherArray.getJSONObject(i);

	        // 현재 시간과 base_time이 일치하는 경우에만 처리
	        if (isCurrentTimeMatchBaseTime(currentTime, baseDate, baseTime)) {
	            String category = weatherObj.getString("category");
	            String minimumtemp = weatherObj.getString("fcstValue");
	            String highesttemp = weatherObj.getString("fcstValue");
	            String currenttemp = weatherObj.getString("fcstValue");
	            String sky = weatherObj.getString("fcstValue");
	            String pty = weatherObj.getString("fcstValue");

	            WeatherDTO weatherDTO = new WeatherDTO(category, minimumtemp, highesttemp, currenttemp, sky, pty);
	            weatherList.add(weatherDTO);
	        }
	        System.out.println("service weather List : "+weatherList);
	    }
		return weatherList;
	}
	
	// 현재 시간과 base_time 비교
	private boolean isCurrentTimeMatchBaseTime(Date currentTime, String baseDate, String baseTime) {
	    // baseDate와 baseTime을 합쳐서 Date 객체로 변환
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, Integer.parseInt(baseDate.substring(0, 4)));
	    cal.set(Calendar.MONTH, Integer.parseInt(baseDate.substring(4, 6)) - 1);
	    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(baseDate.substring(6, 8)));
	    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(baseTime.substring(0, 2)));
	    cal.set(Calendar.MINUTE, Integer.parseInt(baseTime.substring(2, 4)));
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    Date baseDateTime = cal.getTime();
	    System.out.println("weather service basedate : "+baseDateTime);
	    System.out.println("weather service cal : "+cal);
	    // 현재 시간과 base_time 비교
	    return currentTime.equals(baseDateTime);
	}

}
