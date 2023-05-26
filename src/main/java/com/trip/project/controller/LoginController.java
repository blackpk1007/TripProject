package com.trip.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.project.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService lservice;
	
	// 로그인 메인 페이지
	@RequestMapping
	public String loginMain() {

		return "login";
	}

	// 아이디 찾기 페이지
	@RequestMapping("idfindform")
	public String idFindForm() {

		return "idfindform";
	}

	// 아이디 찾기
	@RequestMapping("idfind")
	public String idFind() {

		return "idfind";
	}

	// 비밀번호 찾기 페이지
	@RequestMapping("pwfindform")
	public String pwFindForm() {

		return "pwfindform";
	}

	// 비밀번호 찾기
	@RequestMapping("pwfind")
	public String pwFind() {

		return "pwfind";
	}

	// 회원가입 페이지
	@RequestMapping("registerform")
	public String registerForm() {

		return "registerform";
	}

	// 회원가입
	@RequestMapping("register")
	public String register() {

		return "register";
	}

}
