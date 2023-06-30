package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetailDTO {
	
	private Integer planDetailNumber;
	private String userID;
	private String PlanName;
	private String planDetailDate;
	private String planDetailLon;
	private String planDetailLat;
	private String planDetailColor;
	
}
