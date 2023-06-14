package com.trip.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.OcrDTO;
import com.trip.project.mapper.OcrMapper;

@Service
public class OcrTestImpl implements OcrTest{
	@Autowired
	private OcrMapper ocrMapper;


	@Override
	public OcrDTO selectOne() {
		
		return ocrMapper.selectOne();
	}
}
