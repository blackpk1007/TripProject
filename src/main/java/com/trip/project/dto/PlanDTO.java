package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
	
	private int planNumber;
	private String userID;
	private String planName;
	private String planFirstDate;
	private String planLastDate;
	private int planCount;
}
