package com.trip.project.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.LoginDTO;

@Mapper
public interface LoginMapper {
		
		@Select(" SELECT * FROM login WHERE userID=#{userID} and userPW = #{userPW} ; ")
		public LoginDTO login(LoginDTO dto);
		
		@Insert(" INSERT INTO login VALUES(NULL, #{userName}, #{userID}, #{userPW}, #{userEmail}, 'M', #{userBirth}, default ); ")
		public int regist(LoginDTO dto);
	
}
