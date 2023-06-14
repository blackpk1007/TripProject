package com.trip.project.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
	private int imageNumber;
	private Date imageCreateDate;
	private String imageOriginalName;
	private String imageStoredName;
	
	
}
