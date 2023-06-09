package com.trip.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {
	
	private int placeNumber;
	@JsonProperty("placeName")
	private String placeName;
	private String placeAddress;
	private String placeCategory;
	private String placePhone;
	private int placeGood;
	private String placeContent;
	private String placeInfo;
	private String placeTag1;
	private String placeTag2;
	private String placeTag3;
	private String placeLon;
	private String placeLat;
	private String keyword;

	
}
