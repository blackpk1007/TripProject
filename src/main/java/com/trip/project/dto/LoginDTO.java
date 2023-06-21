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
public class LoginDTO {
	
	private int userNumber;
	private String userName;
	private String userID;
	private String userPW;
	private String userEmail;
	private char userGender;
	private String userBirth;
	private double userTemperature;
	
	private int count;
	private int recommandPlaceNumber;
}
