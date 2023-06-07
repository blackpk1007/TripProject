package com.trip.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.LoginDTO;
import com.trip.project.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public LoginDTO login(LoginDTO dto) {
		
		return loginMapper.login(dto);
	}

	@Override
	public int regist(LoginDTO dto) {
		
		System.out.println(dto.getUserName());
		
		return loginMapper.regist(dto);
		
	}
	

}
