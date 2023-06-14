package com.trip.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
	private String imageOriginalName; //이미지 실제 이름
	private String imageStoredName;	//이미지 저장 이름
	
	private int imageNumber;
}
