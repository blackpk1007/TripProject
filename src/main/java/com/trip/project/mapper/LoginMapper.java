package com.trip.project.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trip.project.dto.LoginDTO;

@Mapper
public interface LoginMapper {
		
		@Select(" SELECT * FROM login WHERE userID=#{userID} ")
		public LoginDTO login(LoginDTO dto);
		
		//유저 정보 가져오기 userupdateform
		@Select(" SELECT * FROM login WHERE userID=#{userID} ")
		public LoginDTO userinfo(String userID);
		
		//아이디찾기 
		@Select(" SELECT userID FROM login WHERE userName=#{userName} and userEmail=#{userEmail}  ")
		public LoginDTO idfind(LoginDTO dto);
		
		//회원가입
		@Insert(" INSERT INTO login VALUES(NULL, #{userName}, #{userID}, #{userPW}, #{userEmail}, #{userGender}, #{userBirth}, default ) ")
		public int regist(LoginDTO dto);
	
		//회원정보수정 - 비밀번호 재설정 
		@Update(" UPDATE login SET userPW=#{userPW} WHERE userID=#{userID} ")
		public int update(LoginDTO dto);
		
		//회원삭제
		@Delete(" DELETE FROM login WHERE userID=#{userID} " )
		public int delete(LoginDTO dto);
		
		//비밀번호 찾기
		@Select(" SELECT userName FROM login WHERE userID=#{userID} and userEmail=#{userEmail} ")
		public LoginDTO pwfind(LoginDTO dto);
		
		//비밀번호찾기- fixform
		@Select(" SELECT userName and userEmail FROM login WHERE userID=#{userID}")
		public LoginDTO idEmail(LoginDTO dto);
		
		//비밀번호찾기-재생성
		@Insert(" UPDATE login SET userPW=#{userPW} WHERE userID=#{userID} and userEmail=#{userEmail} ")
		public int newpw(LoginDTO dto);
		
}
