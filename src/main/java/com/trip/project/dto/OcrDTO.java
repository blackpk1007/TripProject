package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcrDTO {

	private int placeNumber;
	private String placeName;
	private String placeAddress;
}
