package com.trip.project.dto;

import lombok.Data;

@Data
public class LoginDTO {
	
	private int userNumber;
	private String userName;
	private String userID;
	private String userPW;
	private String userEmail;
	private char userGender;
	private String userBirth;
	private double userTemperature;
	
	public LoginDTO() {
		super();
	}
	
	public LoginDTO(int userNumber, String userName, String userID, String userPW, String userEmail, char userGender,
			String userBirth, double userTemperature) {
		super();
		this.userNumber = userNumber;
		this.userName = userName;
		this.userID = userID;
		this.userPW = userPW;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userBirth = userBirth;
		this.userTemperature = userTemperature;
	}


	
	
}
