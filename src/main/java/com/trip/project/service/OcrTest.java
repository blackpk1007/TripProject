package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.OcrDTO;
import com.trip.project.dto.RecommandDTO;

public interface OcrTest {
	public OcrDTO selectByPlaceNumber(int placeNumber);
	public List<RecommandDTO> selectRecommandPlaceNumber(int placeNumber);
	
}
