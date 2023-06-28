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
		if (loginMapper.login(dto) == null) {
			return loginMapper.login(dto);
		}
			
		return loginMapper.login(dto);
	}

	@Override
	public int regist(LoginDTO dto) {
		String encodedPassword= passwordEncoder.encode(dto.getUserPW());
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
	
	@Override
	public LoginDTO idfind(LoginDTO dto) {
		return loginMapper.idfind(dto);
	}

	//비밀번호 찾기 
	@Override
	public LoginDTO pwfind(LoginDTO dto) {
		return loginMapper.pwfind(dto);
	}

	//비밀번호 찾기 - 재설정 
	@Override
	public int newpw(LoginDTO dto) {
		String encodedPassword= passwordEncoder.encode(dto.getUserPW());
		dto.setUserPW(encodedPassword);
		return loginMapper.newpw(dto);
	}
	
}
