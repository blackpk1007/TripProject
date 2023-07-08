package com.trip.project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommandDTO {
	private int recommandNumber;
	private String recommandUserID;
	private int recommandPlaceNumber;
	private String recommandDate;
	private Date recommandNowDate;
	private String placeName;
}
