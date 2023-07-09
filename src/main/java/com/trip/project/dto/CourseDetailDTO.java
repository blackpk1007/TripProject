package com.trip.project.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
	
	private int courseDetailNumber;
	private String courseDetailDate;
	private String courseDetailLon;
	private String courseDetailLat;
	private String courseDetailColor;
	private String shareID;
	private String saveID;
	private String planName;
	private String placeName;
	
	private List<Map<String, String>> lonLatPairs; // 추가된 필드

	// Getter 및 Setter 메서드
	public List<Map<String, String>> getLonLatPairs() {
		return lonLatPairs;
	}

	public void setLonLatPairs(List<Map<String, String>> lonLatPairs) {
		this.lonLatPairs = lonLatPairs;
	}
}
