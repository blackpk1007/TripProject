package com.trip.project.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDTO {
	
	private int communityNumber;
	private String communityTitle;
	private String communityContent;
	private Date communityDate;
	private String communityCategory;
	
	private MultipartFile attachFile;          // 첨부 파일
	private List<MultipartFile> imageFiles;    // 첨부 이미지

}
