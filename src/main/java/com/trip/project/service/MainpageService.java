package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.MainpageDTO;

public interface MainpageService {
	public List<MainpageDTO> selectList();
	public List<MainpageDTO> animalList();
	public List<MainpageDTO> parentsList();
}
