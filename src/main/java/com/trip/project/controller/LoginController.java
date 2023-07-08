package com.trip.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.PlanDTO;
import com.trip.project.service.CommunityService;
import com.trip.project.service.EmailService;
import com.trip.project.service.LoginService;
import com.trip.project.service.PlanService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService lservice;
	
	@Autowired
	private CommunityService cservice;
	
	@Autowired
	private PlanService pservice;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

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
	         return "ID가 일치하지 않습니다.";
	      }

		System.out.println(passwordEncoder.matches(dto.getUserPW(), res.getUserPW()));
		if (!passwordEncoder.matches(dto.getUserPW(), res.getUserPW())) {
			System.out.println("password.");
			System.out.println(dto.getUserPW());
			System.out.println(res.getUserPW());
			
			return "비밀번호가 일치하지 않습니다.";
		}else {
			session.setAttribute("login", res.getUserID());
			session.setMaxInactiveInterval(1800);
		
			return res.getUserID()+"님 로그인 되었습니다.";
		}
	}
	@ResponseBody
	@RequestMapping("/userpasscheck")
	public String userpasscheck(HttpSession session, Model model, LoginDTO dto) {
		LoginDTO res = lservice.login(dto);
		try {
	         if (!dto.getUserID().equals(res.getUserID())) {
	         }
	      }catch(NullPointerException e) {
	         return "ID가 일치하지 않습니다.";
	      }
		System.out.println(passwordEncoder.matches(dto.getUserPW(), res.getUserPW()));
		if (!passwordEncoder.matches(dto.getUserPW(), res.getUserPW())) {
			System.out.println("password.");
			System.out.println(dto.getUserPW());
			System.out.println(res.getUserPW());
			
			return "비밀번호가 일치하지 않습니다.";
		}else{
			return "비밀번호가 확인되었습니다.";
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
	      System.out.println("res = "+res);
	      if(res == null) {
	    	  return "이름과 이메일이 일치하지 않습니다.";
	      }else {
	    	  return dto.getUserName()+"님의 아이디는"+res.getUserID()+"입니다.";
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
	public String pwfind(LoginDTO dto, Model model) {
		LoginDTO res = lservice.pwfind(dto);
	
		System.out.println(res);
		if(res == null) {
			return "아이디와 이메일이 일치하지 않습니다";
		}else {
			//model.addAttribute("userinfo", lservice.userinfo(dto.getUserID()));
//			model.addAttribute("userID", dto.getUserID());
//			System.out.println(dto.getUserID());
		return res.getUserName()+"님의 비밀번호 재설정 페이지로 이동합니다.";
		}
	}
	
	// 비밀번호 재설정페이지
	@RequestMapping("/pwfixform")
	public String pwfixform(HttpSession session, Model model , LoginDTO dto) {
		model.addAttribute("userinfo", lservice.userinfo(dto.getUserID()));
		System.out.println(dto);
		
		return "pwfixform";
	}
	
	//비밀번호 재설정
	@ResponseBody
	@PostMapping("/pwfix")
	public String newpw( LoginDTO dto) {
		int res = lservice.newpw(dto);
		System.out.println(res);
		
		return dto.getUserID()+"님의 패스워드가 수정되었니다.";
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
	
	//아이디중복체크  
	@ResponseBody
	@RequestMapping("/idcheck")
	public int idcheck(LoginDTO dto) {
		int res = lservice.idcheck(dto);
		return res;
	}
	//이메일 인증
	@ResponseBody
	@RequestMapping("/emailConfirm")
	public String emailConfirm(String userEmail, HttpSession session) throws Exception {

	  String confirm = emailService.sendSimpleMessage(userEmail);
	  System.out.println("인증번호"+confirm);
	  session.setAttribute("authcode", confirm);
	  session.setMaxInactiveInterval(180);
	  return confirm;
	}

	// 사용자 마이페이지 메인
	@RequestMapping("/usermain")
	public String userMain(HttpSession session, Model model) {
		String userID = (String) session.getAttribute("login");
		System.out.println(userID);
		
		model.addAttribute("plan", pservice.userPlancount(userID));
		model.addAttribute("plancount", pservice.userPlancount(userID).size());
		System.out.println("일정 : "+ pservice.userPlancount(userID));
		System.out.println(pservice.userPlancount(userID).size());

		model.addAttribute("recommand", pservice.placename(userID) );
		model.addAttribute("recommandcount", pservice.placename(userID).size());
		System.out.println("리뷰 : "+ pservice.placename(userID));
		System.out.println(pservice.placename(userID).size());
		
		model.addAttribute("user2recommand", pservice.user2recommand(userID));
		System.out.println("리뷰 2개이상 : "+pservice.user2recommand(userID));
		
//		model.addAttribute("placename", pservice.placename(userID));
//		System.out.println(pservice.placename(userID));
		
		
		model.addAttribute("community", cservice.usermainCommunity(userID));
		model.addAttribute("communitycount",cservice.usermainCommunity(userID).size());
		System.out.println("게시물 : "+cservice.usermainCommunity(userID));
		System.out.println(cservice.usermainCommunity(userID).size());
		
		
		return "usermain";
		
	}
	

	// 사용자 회원 정보 수정 페이지
	@RequestMapping("/userupdateform")
	public String userUpdateForm(HttpSession session, Model model, LoginDTO dto) {
		String userID = (String) session.getAttribute("login");
//		LoginDTO res = lservice.login(dto);
		model.addAttribute("userinfo", lservice.userinfo(userID));
//		model.addAttribute("userName", lservice.login(dto).getUserName());
//		model.addAttribute("userBirth", lservice.login(dto).getUserBirth());
//		model.addAttribute("userGender", lservice.login(dto).getUserGender());
		
		return "userupdateform";
	}

	//plandelete 삭제
	@ResponseBody
	@RequestMapping("/plandelete")
	public String planDelete(@RequestParam("userID")String userID, @RequestParam("planName") String planName) {
		int res = pservice.planDelete(userID,planName);
		int res1 = pservice.planDetaildelete(userID, planName);
		int res2 = pservice.courseDelete(userID, planName);
		int res3 = pservice.courseDetaildelete(userID, planName);
		System.out.println(res);
		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
		return userID.toString()+"님의"+planName.toString()+"이 삭제 되었습니다.";
	}
	//plan 공유 
	@ResponseBody
	@RequestMapping("/planshare")
	public String planshare(@RequestParam("userID")String userID, @RequestParam("planName") String planName) {
	
		int res = pservice.planShare(userID, planName);
		int res1 = pservice.planDetailshare(userID, planName);
		System.out.println(res);
		System.out.println(res1);
		return userID.toString()+"님의"+planName.toString()+"이 공유 되었습니다.";
	}
	
	// community 공유 
	
	// community 삭제
	@ResponseBody
	@RequestMapping("/communitydelete")
	public String communityDelete(@RequestParam("communityNumber")String communityNumber,@RequestParam("userID")String userID ) {
		int res = cservice.communityDelete(communityNumber, userID);
		System.out.println(res);
		
		return "게시물이 삭제되었습니다.";
	}
	
	
	// 사용자 회원 정보 수정
	@ResponseBody
	@RequestMapping("/userupdate")
	public String userupdate(Model model, LoginDTO dto) {
		
		int res = lservice.update(dto);
		System.out.println(res);
		return dto.getUserID();
	}

	// 사용자 회원 탈퇴
	@ResponseBody
	@RequestMapping("/userdelete")
	public String userDelete(Model model, LoginDTO dto) {
		int res = lservice.delete(dto);
		System.out.println(res);
		
		return dto.getUserID()+"님의 회원탈퇴가 완료되었습니다.";
	}
	
	@RequestMapping("/userinserttest")
	public String userinserttest() {
		
		return "userinserttest";
  }
}
