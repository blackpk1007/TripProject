package com.trip.project.service;

import java.io.IOException;
import java.util.List;

import com.trip.project.dto.FlightStatusDTO;


public interface AirPlaneService {
	
	// 목적지 제주
	public List<FlightStatusDTO> jejuBoarding() throws IOException;
	
	// 출발지 제주
	public List<FlightStatusDTO> jejuArrived() throws IOException;
	
	
}
