package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {
	
	private String day;
	//최저기온
	private int minimumtemp;
	//최고기온
	private int highesttemp;
	//현쟈기온
	private int currenttemp;
	//하늘상태 ﻿맑음(1), 구름많음(3), 흐림(4)
	private int sky;
	//강수상태 ﻿없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
	private int pty;
}
