package com.trip.project.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	private int userNumber;
	private String userName;
	private String userID;
	private String userPW;
	private String userEmail;
	private String userGender;
	private Date userBirth;
	
	public LoginDTO() {
		super();
	}
	
	public LoginDTO(int userNumber, String userName, String userID, String userPW, String userEmail, String userGender,
			Date userBirth) {
		super();
		this.userNumber = userNumber;
		this.userName = userName;
		this.userID = userID;
		this.userPW = userPW;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userBirth = userBirth;
	}
	
}
