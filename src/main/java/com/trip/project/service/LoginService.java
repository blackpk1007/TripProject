package com.trip.project.service;

import com.trip.project.dto.LoginDTO;

public interface LoginService {

	public LoginDTO login(LoginDTO dto);
	
	public int regist(LoginDTO dto);
	
	public int update(LoginDTO dto);
	
	public int delete(LoginDTO dto);
	
	public LoginDTO idfind(LoginDTO dto);
	
	//비밀번호 찾기 
	public LoginDTO pwfind(LoginDTO dto);
	//비밀번호 찾기-재생성 
	public int newpw(LoginDTO dto);
	// userid로 유저 정보 들고오기 userupdateform
	public LoginDTO userinfo(String userID);
}
