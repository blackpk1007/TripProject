package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
	
	private int courseDetailNumber;
	private String courseDetailDAte;
	private String courseDtailLon;
	private String courseDetailLat;
	private String courseDetailColor;
	private String userID;
	private String planName;
}
