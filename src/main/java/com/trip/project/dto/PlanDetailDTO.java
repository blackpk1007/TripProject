package com.trip.project.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetailDTO {
	
	private Integer planDetailNumber;
	private String shareID;
	private String saveID;
	private String PlanName;
	private String planDetailDate;
	private String planDetailLon;
	private String planDetailLat;
	private String planDetailColor;

}
