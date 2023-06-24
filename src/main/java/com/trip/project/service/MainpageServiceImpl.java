package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.MainpageDTO;
import com.trip.project.mapper.MainpageMapper;

@Service
public class MainpageServiceImpl implements MainpageService{
	
	@Autowired
	private MainpageMapper mainpageMapper;
	
	@Override
	public List<MainpageDTO> selectList() {
		return mainpageMapper.selectList();
	}
	
	@Override
	public List<MainpageDTO> animalList() {
		return mainpageMapper.animalList();
	}
	
	@Override
	public List<MainpageDTO> parentsList() {
		return mainpageMapper.parentsList();
	}
}
