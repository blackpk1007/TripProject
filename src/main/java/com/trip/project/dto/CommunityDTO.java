package com.trip.project.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityDTO {
	
	private int communityNumber;
	private String communityTitle;
	private String communityContent;
	private Date communityDate;
	private String communityCategory;
	
	public CommunityDTO() {
		super();
	}
	
	public CommunityDTO(int communityNumber, String communityTitle, String communityContent, Date communityDate,
			String communityCategory) {
		super();
		this.communityNumber = communityNumber;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityDate = communityDate;
		this.communityCategory = communityCategory;
	}
	
	
}
