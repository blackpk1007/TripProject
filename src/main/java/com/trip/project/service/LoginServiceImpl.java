package com.trip.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trip.project.dto.LoginDTO;
import com.trip.project.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public LoginDTO login(LoginDTO dto) {

		return loginMapper.login(dto);
	}

	@Override
	public int regist(LoginDTO dto) {
		String encodedPassword= passwordEncoder.encode(dto.getUserPW());
		System.out.println(encodedPassword);
		dto.setUserPW(encodedPassword);
		
		return loginMapper.regist(dto);

	}

	@Override
	public int update(LoginDTO dto) {
		return loginMapper.update(dto);
	}

	@Override
	public int delete(LoginDTO dto) {
		return loginMapper.delete(dto);
	}

}
