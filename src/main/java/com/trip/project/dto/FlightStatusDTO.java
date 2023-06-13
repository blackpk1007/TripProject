package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightStatusDTO {
	private String airlineKorean;
	private String arrivedKor;
	private String boardingKor;
	private String flightDate;
	private String rmkKor;
	private int std;
	private int etd;
	private String airFln;
}
