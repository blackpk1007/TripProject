package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
	private int courseNumber;
	private String courseFirstDate;
	private String courseLastDate;
	private String coursetravelDate;
	private int courseCount;
	private String shareID;
	private String saveID;
	private String planName;
}
