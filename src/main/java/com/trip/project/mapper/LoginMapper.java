package com.trip.project.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trip.project.dto.LoginDTO;

@Mapper
public interface LoginMapper {
		
		@Select(" SELECT * FROM login WHERE userID=#{userID} and userPW = #{userPW} ; ")
		public LoginDTO login(LoginDTO dto);
		
		//회원가입
		@Insert(" INSERT INTO login VALUES(NULL, #{userName}, #{userID}, #{userPW}, #{userEmail}, 'M', #{userBirth}, default ); ")
		public int regist(LoginDTO dto);
	
		//회원정보수정
		@Update(" UPDATE login SET userPW=#{userPW} WHERE userID=#{userID} ")
		public int update(LoginDTO dto);
		
		//회원삭제
		@Delete(" DELETE FROM login WHERE userID=#{userID} " )
		public int delete(LoginDTO dto);
}
