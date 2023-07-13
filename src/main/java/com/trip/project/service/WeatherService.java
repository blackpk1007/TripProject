package com.trip.project.service;

import java.io.IOException;
import java.util.List;

import com.trip.project.dto.WeatherDTO;

public interface WeatherService {

		// 제주 날씨
		public WeatherDTO Jeju() throws IOException, InterruptedException;
		
		// 서귀포 날씨
		public WeatherDTO Seogwipo() throws IOException, InterruptedException;
}
