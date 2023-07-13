package com.trip.project.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.dto.ImageDTO;
import com.trip.project.dto.SearchDTO;
import com.trip.project.dto.UploadFile;
import com.trip.project.file.FileStore;
import com.trip.project.paging.PagingResponse;
import com.trip.project.service.CommunityService;


@Controller
@RequestMapping("/community")
public class CommunityController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService cService;

	// 커뮤니티 메인 페이지
	@RequestMapping("/communitymain")
	public String cummunityMain(Model model, @ModelAttribute("params") final SearchDTO params,HttpSession session ) {
		session.getAttribute("login");
		model.addAttribute("session", session);
		model.addAttribute("response", cService.selectCommunity(params));
		model.addAttribute("params", params);
		model.addAttribute("communityCategory","all");
		
		return "communitymain";
	}

	// 커뮤니티 상세 페이지
	

	// 커뮤니티 글쓰기 페이지
	@RequestMapping("/communitywriteform")
	public String communityWriteForm(HttpSession session, Model model) {
		session.getAttribute("login");
		model.addAttribute("session", session);
		
		return "communitywriteform";
	}

	// 커뮤니티 글쓰기
	@RequestMapping("/communitywrite")
	public String communityWrite(CommunityDTO dto, Model modelD) throws IOException {
		
		// List<UploadFile> imagefile = FileStore.storeFiles(dto.getImageFiles());
		UploadFile file = FileStore.storeFile(dto.getAttachFile());
		
		// 게시글 insert
		int communityInsertRes = cService.insert(dto);
		int imageInsertRes = 0;
		
		//file을 선택했을때 
		if (file != null) {
			//방금 INSERT한 게시글의 번호를 SELECT한 다음 UploadFile 객체에 저장
			CommunityDTO tmp = cService.ComunityselectOne();
			file.setImageNumber(tmp.getCommunityNumber());
			//image insert
			imageInsertRes = cService.imageInsert(file);
			
			if (communityInsertRes > 0 &&  imageInsertRes> 0) {
				return "redirect:/community/communitymain";
			} else {
				return "redirect:/community/communitywriteform";
			}
		}else {
			if (communityInsertRes > 0 ) {
				return "redirect:/community/communitymain";
			} else {
				return "redirect:/community/communitywriteform";
			}
		}

		 
	}
	
	@RequestMapping("/communitydetail")
	public String communityDetail(Model model, int communityNumber, HttpSession session) {
		model.addAttribute("dto", cService.selectOne(communityNumber));

		ImageDTO imgDto = cService.selectOneImg(communityNumber);
		model.addAttribute("image", imgDto);
		
		session.getAttribute("login");
		model.addAttribute("session", session);
		
		return "communitydetail";
	}

	// 커뮤니티 수정 페이지
	@RequestMapping("/communityupdateform")
	public String communityUpdateForm(Model model, int communityNumber) {
		ImageDTO imgDto = cService.selectOneImg(communityNumber);
		model.addAttribute("dto", cService.selectOne(communityNumber));
		model.addAttribute("image", imgDto);
		
		return "communityupdateform";
	}

	// 커뮤니티 수정
	@RequestMapping("/communityupdate")
	public String communityUpdate(CommunityDTO dto, ImageDTO imageDto, int communityNumber) throws IOException {
		UploadFile file = FileStore.storeFile(dto.getAttachFile());
		System.out.println("updatefile="+file);
		// 게시글 update
		int communityUpdateRes = cService.update(dto);
		System.out.println("communityudate="+communityUpdateRes);
		int imageUpdateRes = 0;
		int imageInsertRes = 0;
		
		//file을 선택했을때 
		if (file != null) {
			
			CommunityDTO tmp = cService.selectOne(communityNumber);
			file.setImageNumber(tmp.getCommunityNumber());
			System.out.println("updatefilenumber="+file);
			//image update
			imageUpdateRes = cService.updateImg(file);
			
			if (communityUpdateRes > 0 &&  imageUpdateRes> 0) {
				return "redirect:/community/communitydetail?communityNumber="+dto.getCommunityNumber();
				
			}else if(communityUpdateRes > 0 &&  imageUpdateRes == 0) {
				
				imageInsertRes = cService.imageInsert(file);
				
				if(communityUpdateRes > 0 &&  imageInsertRes > 0) {
					return "redirect:/community/communitydetail?communityNumber="+dto.getCommunityNumber();
				}else {
					return "redirect:/community/communityupdate?communityNumber="+dto.getCommunityNumber();
				}
				
			}else {
				return "redirect:/community/communityupdate?communityNumber="+dto.getCommunityNumber();
			}
		}else {
			if (communityUpdateRes > 0 ) {
				return "redirect:/community/communitydetail?communityNumber="+dto.getCommunityNumber();
			} else {
				return "redirect:/community/communityupdate?communityNumber="+dto.getCommunityNumber();
			}
		}
		
	}

	// 커뮤니티 삭제
	@RequestMapping("/communitydelete")
	public String communityDelete(int communityNumber) {
		if (cService.delete(communityNumber) > 0) {
			return "redirect:/community/communitymain";
		} else {
			return "redirect:/community/communitydetail?communityNumber=" + communityNumber;
		}
	}

	@ResponseBody
	@RequestMapping("/image/{filename}")
	public UrlResource showImage(@PathVariable String filename) throws MalformedURLException {
	    System.out.println("show:"+filename);
		return new UrlResource("file:" + FileStore.getFullPath(filename));

	}
	@ResponseBody
	@PostMapping("/selectbox")
	public PagingResponse<CommunityDTO> SelectBox(@RequestBody CommunityDTO dto, SearchDTO params) {
		PagingResponse<CommunityDTO> data = null;
		
		if("all".equals(dto.getCommunityCategory())) {
			data = cService.selectCommunity(params);
		}else if("tip".equals(dto.getCommunityCategory())) {
			data = (PagingResponse<CommunityDTO>) cService.selectCommunityTip(dto.getCommunityCategory(), params);
		}else if("review".equals(dto.getCommunityCategory())) {
			data = (PagingResponse<CommunityDTO>) cService.selectCommunityReview(dto.getCommunityCategory(), params);
		}
		
		
		return data;
	}
	
	@GetMapping("/pagingSelect")
	public String pagingSelect(CommunityDTO dto, SearchDTO params,Model model) {
		
		PagingResponse<CommunityDTO> data = null;
		if("all".equals(dto.getCommunityCategory())) {
			data = cService.selectCommunity(params);
		}else if("tip".equals(dto.getCommunityCategory())) {
			data = (PagingResponse<CommunityDTO>) cService.selectCommunityTip(dto.getCommunityCategory(), params);
		}else if("review".equals(dto.getCommunityCategory())) {
			data = (PagingResponse<CommunityDTO>) cService.selectCommunityReview(dto.getCommunityCategory(), params);
		}				
		
		model.addAttribute("response", data);
		model.addAttribute("params", params);
		model.addAttribute("communityCategory", dto.getCommunityCategory());
		return "communitymain";
	}
	

}
