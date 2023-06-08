package com.trip.project.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trip.project.dto.LoginDTO;
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

	@PostMapping("/logincheck")
	public String login(Model model, LoginDTO dto) {
		LoginDTO res = lservice.login(dto);
		if (res != null) {
			return "main";
		} else {
			return "login";
		}
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
	@RequestMapping("/registerform")
	public String registerForm() {

		return "registerform";

	}

	// 회원가입
	@RequestMapping("/register")
	public String register(Model model, LoginDTO dto) {

		int res = lservice.regist(dto);
		if (res != 0) {
			System.out.println(dto.getUserName());
			return "main";
		} else {
			return "registerform";
		}
	}

	// 사용자 마이페이지 메인
	@RequestMapping("/usermain")
	public String userMain() {

		return "usermain";
	}

	// 사용자 회원 정보 수정 페이지
	@RequestMapping("userupdateform")
	public String userUpdateForm() {

		return "userupdateform";
	}

	// 사용자 회원 정보 수정
	@RequestMapping("userupdate")
	public String userupdate() {

		return "userupdate";
	}

	// 사용자 회원 탈퇴
	@RequestMapping("userdelete")
	public String userDelete() {

		return "userdelete";
	}

}
