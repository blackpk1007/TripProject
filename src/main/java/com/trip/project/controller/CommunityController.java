package com.trip.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {
	
	// 커뮤니티 메인 페이지
	@RequestMapping
	public String cummunityMain() {
		
		return "communitymain";
	}
	
	// 커뮤니티 상세 페이지
	@RequestMapping("/communitydetail")
	public String communityDetail() {
		
		return "communitydetail";
	}
	
	// 커뮤니티 글쓰기 페이지
	@RequestMapping("/communitywriteform")
	public String communityWriteForm() {
		
		return "communitywriteform";
	}
	
	// 커뮤니티 글쓰기
	@RequestMapping("/communitywrite")
	public String communityWrite() {
		
		return "communitywrite";
	}
	
	// 커뮤니티 수정 페이지
	@RequestMapping("/communityupdateform")
	public String communityUpdateForm() {
		
		return "communityupdateform";
	}
	
	// 커뮤니티 수정
	@RequestMapping("/communityupdate")
	public String communityUpdate(){
		
		return "communityupdate";
	}
	
	// 커뮤니티 삭제
	@RequestMapping("/communitydelete")
	public String communityDelete() {
		
		return "communitydelete";
	}
}








