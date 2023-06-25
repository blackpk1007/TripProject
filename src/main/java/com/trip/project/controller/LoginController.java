package com.trip.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.project.dto.LoginDTO;
import com.trip.project.service.CommunityService;
import com.trip.project.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService lservice;
	
	@Autowired
	private CommunityService cservice;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// 로그인 메인 페이지
	@RequestMapping
	public String loginMain() {

		return "login";
	}
	
	@ResponseBody
	@PostMapping("/logincheck")
	public String login(HttpSession session, Model model, LoginDTO dto) {
		LoginDTO res = lservice.login(dto);

		try {
	         if (!dto.getUserID().equals(res.getUserID())) {
	         }
	      }catch(NullPointerException e) {
	         return "ID 없음";
	      }

		System.out.println(passwordEncoder.matches(dto.getUserPW(), res.getUserPW()));
		if (!passwordEncoder.matches(dto.getUserPW(), res.getUserPW())) {
			System.out.println("password.");
			System.out.println(dto.getUserPW());
			System.out.println(res.getUserPW());
			
			return "비밀번호일치하지않습니다.";
		}else {
			session.setAttribute("login", res.getUserName());
			session.setMaxInactiveInterval(1800);
		
			return res.getUserName()+"님 로그인 되었습니다.";
		}
		
  }
	// 로그아웃 - 메인
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	// 아이디 찾기 페이지
	@RequestMapping("/idfindform")
	public String idFindForm() {

		return "idfindform";
	}

	// 아이디 찾기
	@ResponseBody
	   @PostMapping("/idfind")
	   public String idFind(LoginDTO dto) {
	      System.out.println("controller :"+dto.getUserName());
	      System.out.println("controller :"+dto.getUserEmail());
	      
	      LoginDTO res = lservice.idfind(dto);
	      if(res == null) {
	    	  return "아이디와 이메일이 일치하지 않습니다.";
	      }else {
	    	  return res.getUserID()+"";
	      }
	      
	   }


	// 비밀번호 찾기 페이지
	@RequestMapping("/pwfindform")
	public String pwFindForm() {

		return "pwfindform";
	}

	// 비밀번호 찾기
	@ResponseBody
	@PostMapping("/pwfind")
	public String pwfind(LoginDTO dto) {
		LoginDTO res = lservice.pwfind(dto);
		System.out.println(res);
		
		return res.getUserName();
	}
	
	// 비밀번호 재설정페이지
	@PostMapping("/pwfixform")
	public String pwfixform(Model model , LoginDTO dto) {
		System.out.println(dto.getUserID());
		System.out.println(dto.getUserEmail());
		
		model.addAttribute("dto",dto);
		return "pwfixform";
	}
	
	//비밀번호 재설정
	@ResponseBody
	@PostMapping("/pwfix")
	public String pwfix( LoginDTO dto) {
		int res = lservice.newpw(dto);
		System.out.println(res);
		
		return dto.getUserName();
	}

	// 회원가입 페이지
	@RequestMapping("/registerform")
	public String registerForm() {
		
		return "registerform";

	}

	/**
	 * 
	 * @param model
	 * @param dto 로그인된 사람
	 * @return 그 다음 뷰
	 */
	// 회원가입
	@RequestMapping("/register")
	public String register(Model model, LoginDTO dto) {
		int res = lservice.regist(dto);

		if (res != 0) {
			System.out.println(dto.getUserName());
			model.addAttribute("message", "회원가입 완료.");
			return "redirect:/";
		} else {
			model.addAttribute("error", "재등록.");
			return "redirect:/registerform";
		}

	}

	// 사용자 마이페이지 메인
	@RequestMapping("/usermain")
	public String userMain(HttpSession session, Model model) {
		String userID = (String) session.getAttribute("login");
		model.addAttribute("community", cservice.usermainCommunity(userID));
		System.out.println(userID);
		System.out.println("controller : "+cservice.usermainCommunity(userID));
		return "usermain";
		
	}

	// 사용자 회원 정보 수정 페이지
	@RequestMapping("/userupdateform")
	public String userUpdateForm() {
		return "userupdateform";
	}

	// 사용자 회원 정보 수정
	@RequestMapping("/userupdate")
	public String userupdate(Model model, LoginDTO dto) {
		int res = lservice.update(dto);

		if (res != 0) {
			System.out.println(dto.getUserName());
			model.addAttribute("message", "회원정보 수정 완료.");
			return "redirect:/usermain";
		} else {
			model.addAttribute("error", "회원정보 수정 실패.");
			return "redirect:/userupdateform";
		}
	}

	// 사용자 회원 탈퇴
	@RequestMapping("/userdelete")
	public String userDelete(Model model, LoginDTO dto) {
		int res = lservice.delete(dto);

		if (res != 0) {
			System.out.println(dto.getUserName());
			model.addAttribute("message", "회원정보 삭제 완료.");
			return "redirect:/usermain";
		} else {
			model.addAttribute("error", "삭제실패.");
			return "redirect:/usermain";
		}
	}
	
	@RequestMapping("/userinserttest")
	public String userinserttest() {
		
		return "userinserttest";
  }
}
