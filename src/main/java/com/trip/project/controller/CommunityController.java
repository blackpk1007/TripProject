package com.trip.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityService cService;
	
	// 커뮤니티 메인 페이지
	@RequestMapping("/communitymain")
	public String cummunityMain(Model model) {
		logger.info("COMMUNITY MAIN");
		model.addAttribute("list", cService.selectCommunity());
		return "communitymain";
	}
	
	// 커뮤니티 상세 페이지
	@RequestMapping("/communitydetail")
	public String communityDetail(Model model, int communityNumber) {
		logger.info("COMMUNITY DETAIL");
	    model.addAttribute("dto", cService.selectOne(communityNumber));
		return "communitydetail";
	}
	
	// 커뮤니티 글쓰기 페이지
	@RequestMapping("/communitywriteform")
	public String communityWriteForm() {
		logger.info("COMMUNITY WRITE FORM");
		return "communitywriteform";
	}
	
	// 커뮤니티 글쓰기
	@RequestMapping("/communitywrite")
	public String communityWrite(CommunityDTO dto) {
		logger.info("COMMUNITY WRITE");
		if(cService.insert(dto)>0) {
			return "redirect:/community/communitymain";
		}else {
			return "redirect:/community/communitywriteform";
		}
		
	}
	
	// 커뮤니티 수정 페이지
	@RequestMapping("/communityupdateform")
	public String communityUpdateForm(Model model, int communityNumber) {
		logger.info("UPDATEFORM COMMUNITY");
		model.addAttribute("dto", cService.selectOne(communityNumber));
		return "communityupdateform";
	}
	
	// 커뮤니티 수정
	@RequestMapping("/communityupdate")
	public String communityUpdate(CommunityDTO dto){
		logger.info("UPDATE COMMUNITY");
		if(cService.update(dto)>0) {
			return "redirect:/communitymain";
		}else {
			return "redirect:/communityupdateform";
		}
	}
	
	// 커뮤니티 삭제
	@RequestMapping("/communitydelete")
	public String communityDelete(int communityNumber) {
		logger.info("DELETE COMMUNITY");
		if(cService.delete(communityNumber)>0) {
			return "redirect:/community";
		}else {
			return "redirect:/detail?communityNumber"+communityNumber;
		}
	}
	
	
}








