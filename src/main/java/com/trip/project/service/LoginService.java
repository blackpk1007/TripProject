package com.trip.project.service;

import com.trip.project.dto.LoginDTO;

public interface LoginService {

	public LoginDTO login(LoginDTO dto);
	
	public int regist(LoginDTO dto);
	
}
