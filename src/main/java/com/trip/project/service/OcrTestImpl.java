package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.OcrDTO;
import com.trip.project.dto.RecommandDTO;
import com.trip.project.mapper.OcrMapper;

@Service
public class OcrTestImpl implements OcrTest{
	@Autowired
	private OcrMapper ocrMapper;


	
	@Override
	public OcrDTO selectByPlaceNumber(int placeNumber) {
		return ocrMapper.selectByPlaceNumber(placeNumber);
	}
	
	@Override
	public List<RecommandDTO> selectRecommandPlaceNumber(int placeNumber) {
		return ocrMapper.selectRecommandPlaceNumber(placeNumber);
	}
	

}
