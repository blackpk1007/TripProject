package com.trip.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.trip.project.dto.FlightStatusDTO;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {

	@Override
	public List<FlightStatusDTO> jejuBoarding() throws IOException {
		List<FlightStatusDTO> flightStatusList = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		int currentHour = now.getHour();

		String[] airportCodes = { "GMP", "PUS", "TAE", "CJJ", "KWJ", "MWX", "YNY", "USN", "HIN", "KPO", "JUV", "RSU",
				"WJU", "SSN" };

		for (int hour = currentHour; hour < currentHour + 2; hour++) {
			if (hour == currentHour) {
				String startTime = String.format("%02d00", hour);
				String endTime = String.format("%02d59", hour);

				for (String airportCode : airportCodes) {
					StringBuilder urlBuilder = new StringBuilder(
							"http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList"); /* URL */
					urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
							+ "=1kainzqQnmN7SXsZINtwO%2FxHhS9Vym1t4CDA1aTLEbOtE%2BInS7RhlsMw%2F4vTuOAaepTP5Cv8gt1sge%2Bhc%2BDJtA%3D%3D"); /*
																																			 * Service
																																			 * Key
																																			 */
					urlBuilder.append("&" + URLEncoder.encode("schLineType", "UTF-8") + "="
							+ URLEncoder.encode("D", "UTF-8")); /* 국내 / 국제 */
					urlBuilder.append("&" + URLEncoder.encode("schIOType", "UTF-8") + "="
							+ URLEncoder.encode("O", "UTF-8")); /* 도착 / 출발 */
					urlBuilder.append("&" + URLEncoder.encode("schAirCode", "UTF-8") + "="
							+ URLEncoder.encode(airportCode, "UTF-8")); /* 공항코드 */
					urlBuilder.append("&" + URLEncoder.encode("schStTime", "UTF-8") + "="
							+ URLEncoder.encode(startTime, "UTF-8")); /* 예정시간 */
					urlBuilder.append("&" + URLEncoder.encode("schEdTime", "UTF-8") + "="
							+ URLEncoder.encode(endTime, "UTF-8")); /* 변경시간 */
					urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
							+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
					urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
							+ URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
					URL url = new URL(urlBuilder.toString());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Content-type", "application/json");
					StringBuilder sb = new StringBuilder();
					try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
						String line;
						while ((line = rd.readLine()) != null) {
							sb.append(line);
						}
					} catch (IOException e) {
						// Error handling
					} finally {
						conn.disconnect();
					}
					String flightStatusJson = sb.toString();
					// XML 파싱
					JSONObject jsonObject = XML.toJSONObject(flightStatusJson);
					JSONObject response = jsonObject.getJSONObject("response");
					if (!response.has("body")) {
						return null;
					}
					JSONObject body = response.getJSONObject("body");

					// Check if "items" field is a JSONObject
					if (body.has("items") && body.get("items") instanceof JSONObject) {
						JSONObject items = body.getJSONObject("items");
						Object item = items.get("item");
						if (item instanceof JSONArray) {
							JSONArray jsonArray = (JSONArray) item;
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject flightObject = jsonArray.getJSONObject(i);
								String arrivedKor = flightObject.getString("arrivedKor");
								if (arrivedKor != null && "제주".equals(arrivedKor)) {
									FlightStatusDTO flightStatus = createFlightStatus(flightObject);
									flightStatusList.add(flightStatus);
								}
							}
						} else if (item instanceof JSONObject) {
							JSONObject flightObject = (JSONObject) item;
							String arrivedKor = flightObject.optString("arrivedKor");
							if (arrivedKor != null && "제주".equals(arrivedKor)) {
								FlightStatusDTO flightStatus = createFlightStatus(flightObject);
								flightStatusList.add(flightStatus);
							}
						}
					}
				}
			}
		}

		return flightStatusList;
	}

	@Override
	public List<FlightStatusDTO> jejuArrived() throws IOException {
		List<FlightStatusDTO> flightStatusList = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		int currentHour = now.getHour();

		for (int hour = currentHour; hour < currentHour + 2; hour++) {
			if (hour == currentHour) {
				String startTime = String.format("%02d00", hour);
				String endTime = String.format("%02d59", hour);

				StringBuilder urlBuilder = new StringBuilder(
						"http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList"); /* URL */
				urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
						+ "=1kainzqQnmN7SXsZINtwO%2FxHhS9Vym1t4CDA1aTLEbOtE%2BInS7RhlsMw%2F4vTuOAaepTP5Cv8gt1sge%2Bhc%2BDJtA%3D%3D"); /*
																																		 * Service
																																		 * Key
																																		 */
				urlBuilder.append("&" + URLEncoder.encode("schLineType", "UTF-8") + "="
						+ URLEncoder.encode("D", "UTF-8")); /* 국내 / 국제 */
				urlBuilder.append("&" + URLEncoder.encode("schIOType", "UTF-8") + "="
						+ URLEncoder.encode("O", "UTF-8")); /* 도착 / 출발 */
				urlBuilder.append("&" + URLEncoder.encode("schAirCode", "UTF-8") + "="
						+ URLEncoder.encode("CJU", "UTF-8")); /* 공항코드 */
				urlBuilder.append("&" + URLEncoder.encode("schStTime", "UTF-8") + "="
						+ URLEncoder.encode(startTime, "UTF-8")); /* 예정시간 */
				urlBuilder.append("&" + URLEncoder.encode("schEdTime", "UTF-8") + "="
						+ URLEncoder.encode(endTime, "UTF-8")); /* 변경시간 */
				urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
						+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
				urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
						+ URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
				URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/json");
				StringBuilder sb = new StringBuilder();
				try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
					String line;
					while ((line = rd.readLine()) != null) {
						sb.append(line);
					}
				} catch (IOException e) {
					// Error handling
				} finally {
					conn.disconnect();
				}
				String flightStatusJson = sb.toString();
				// XML 파싱
				JSONObject jsonObject = XML.toJSONObject(flightStatusJson);
				JSONObject response = jsonObject.getJSONObject("response");
				if (!response.has("body")) {
					return null;
				}
				JSONObject body = response.getJSONObject("body");

				// Check if "items" field is a JSONObject
				if (body.has("items") && body.get("items") instanceof JSONObject) {
					JSONObject items = body.getJSONObject("items");
					Object item = items.get("item");
					if (item instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) item;
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject flightObject = jsonArray.getJSONObject(i);
							FlightStatusDTO flightStatus = createFlightStatus(flightObject);
							flightStatusList.add(flightStatus);
						}
					} else if (item instanceof JSONObject) {
						JSONObject flightObject = (JSONObject) item;
						FlightStatusDTO flightStatus = createFlightStatus(flightObject);
						flightStatusList.add(flightStatus);
					}
				}
			}
		}

		return flightStatusList;
	}

	private FlightStatusDTO createFlightStatus(JSONObject flightObject) {
		FlightStatusDTO flightStatus = new FlightStatusDTO();
		flightStatus.setAirlineKorean(flightObject.optString("airlineKorean"));
		flightStatus.setArrivedKor(flightObject.optString("arrivedKor"));
		flightStatus.setBoardingKor(flightObject.optString("boardingKor"));
		flightStatus.setFlightDate(flightObject.optString("flightDate"));
		flightStatus.setRmkKor(flightObject.optString("rmkKor"));
		if (!flightObject.isNull("std")) {
	           flightStatus.setStd(flightObject.getInt("std"));
	       }
	    if (!flightObject.isNull("etd")) {
	        flightStatus.setEtd(flightObject.getInt("etd"));
	    }
		flightStatus.setAirFln(flightObject.optString("airFln"));
		
		return flightStatus;
	}
}
