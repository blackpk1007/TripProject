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

	//회원정보 수정 - 비밀번호 재설정 
	@Override
	public int update(LoginDTO dto) {
		String encodedPassword= passwordEncoder.encode(dto.getUserPW());
		dto.setUserPW(encodedPassword);
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

	@Override
	public LoginDTO userinfo(String userID) {
		
		return loginMapper.userinfo(userID);
	}

	@Override
	public LoginDTO idEmail(LoginDTO dto) {

		return loginMapper.idEmail(dto);
	}

	@Override
	public int idcheck(LoginDTO dto) {
			if (0 != loginMapper.idcheck(dto)) {
				//중복
				return 1;
			}else {
				return 0;
			}
	}
	
}
