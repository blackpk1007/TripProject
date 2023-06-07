package com.trip.project.dto;

import lombok.Data;

@Data
public class PlaceDTO {
	
	private int placeNumber;
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
	
	public PlaceDTO() {
		super();
	}
	
	public PlaceDTO(int placeNumber, String placeName, String placeAddress, String placeCategory, String placePhone,
			int placeGood, String placeContent, String placeInfo, String placeTag1, String placeTag2, String placeTag3,
			String placeLon, String placeLat) {
		super();
		this.placeNumber = placeNumber;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
		this.placeCategory = placeCategory;
		this.placePhone = placePhone;
		this.placeGood = placeGood;
		this.placeContent = placeContent;
		this.placeInfo = placeInfo;
		this.placeTag1 = placeTag1;
		this.placeTag2 = placeTag2;
		this.placeTag3 = placeTag3;
		this.placeLon = placeLon;
		this.placeLat = placeLat;
	}


	
	
	
}