package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
	
	private Integer planNumber;
	private String shareID;
	private String saveID;
	private String planName;
	private String planFirstDate;
	private String planLastDate;
	private int planCount;
}
