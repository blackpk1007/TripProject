package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.CourseDetailDTO;
import com.trip.project.dto.PlaceDTO;

@Mapper
public interface CourseMapper {
	
	@Select(" select * from course order by courseFirstDate desc ")
	List<CourseDTO> courseDateSort();
	
	@Select(" select * from course order by courseCount desc ")
	List<CourseDTO> courseCountSort();
	
	@Select(" select * from course order by courseCount desc limit 6")
	List<CourseDTO> courseCountMain();
	
	@Select(" select * from course order by courseFirstDate desc ")
	List<CourseDTO> courseList();
	
	@Select(" select * from course where userID = #{userID} and planName = #{planName} ")
	CourseDTO courseDetail(String userID, String planName);
	
	@Select(" select placeName from (select *, ROW_NUMBER() OVER (PARTITION BY d.courseDetailDate) AS rn from place p join courseDetail d on p.placeLon = d.courseDetailLon and p.placeLat = d.courseDetailLat "
			+ " where userID = #{userID} and planName = #{planName}) sub "
			+ " WHERE rn = 1 ")
	List<PlaceDTO> courseImage(String userID, String planName);
	
	@Select({
	    "<script>",
	    "SELECT * FROM course",
	    "WHERE MONTH(STR_TO_DATE(courseFirstDate, '%Y-%m-%d')) IN",
	    "<foreach item='month' collection='months' open='(' separator=',' close=')'>",
	    "#{month}",
	    "</foreach>",
	    "</script>"
	})
	List<CourseDTO> courseseason(@Param("months") List<Integer> months);
	
	@Select({
	    "<script>",
	    "SELECT * FROM course",
	    "WHERE",
	    "<foreach item='day' collection='days' separator=' OR'>",
	    "<if test='day == 10'>",
	    "coursetravelDate >= #{day}",
	    "</if>",
	    "<if test='day != 10'>",
	    "<choose>",
	    "<when test='days.contains(10)'>",
	    "coursetravelDate IN (#{day}) OR coursetravelDate >= 10",
	    "</when>",
	    "<otherwise>",
	    "coursetravelDate IN (#{day})",
	    "</otherwise>",
	    "</choose>",
	    "</if>",
	    "</foreach>",
	    "</script>"
	})
	List<CourseDTO> coursedays(@Param("days") List<Integer> days);
	
	@Select({
	    "<script>",
	    "SELECT * FROM course",
	    "WHERE",
	    "<choose>",
	    "<when test='days.contains(10)'>",
	    "(coursetravelDate IN",
	    "<foreach item='day' collection='days' open='(' separator=',' close=')'>",
	    "#{day}) OR coursetravelDate >= 10" ,
	    "</foreach>",
	    "AND MONTH(STR_TO_DATE(courseFirstDate, '%Y-%m-%d')) IN",
	    "<foreach item='month' collection='months' open='(' separator=',' close=')'>",
	    "#{month}",
	    "</foreach>",
	    "</when>",
	    "<otherwise>",
	    "coursetravelDate IN",
	    "<foreach item='day' collection='days' open='(' separator=',' close=')'>",
	    "#{day}",
	    "</foreach>",
	    "OR MONTH(STR_TO_DATE(courseFirstDate, '%Y-%m-%d')) IN",
	    "<foreach item='month' collection='months' open='(' separator=',' close=')'>",
	    "#{month}",
	    "</foreach>",
	    "</otherwise>",
	    "</choose>",
	    "</script>"
	})

	List<CourseDTO> courseDaysSeasons(@Param("days") List<Integer> days, @Param("months") List<Integer> months);
	
//	@Select(" select * from courseDetail where userID = #{userID} and planName = #{planName} ")
//	List<CourseDetailDTO> courseDetailList(String userID, String planName);
	
	@Select(" select * from place p join courseDetail d on p.placeLon = d.courseDetailLon and p.placeLat = d.courseDetailLat"
			+ " where userID = #{userID} and planName = #{planName} order by courseDetailDate ")
	List<CourseDetailDTO> courseDetailList(String userID, String planName);
	
	@Update(" update course set courseCount = courseCount + 1 where userID = #{userID} and planName = #{planName} ")
	int courseListCount(String userID, String planName);
	
	@Insert(" INSERT INTO course (courseFirstDate, courseLastDate, coursetravelDate, courseCount, userID, planName )"
			+ "select planFirstDate, planLastDate, #{datecount}, planCount, userID, planName "
			+ "from plan "
			+ "WHERE planName=#{planName} AND userID=#{userID} ")
	int courseShare(String userID, String planName, @Param("datecount")int datecount);
	
	@Insert(" INSERT INTO courseDetail (courseDetailDate, courseDetailLon, courseDetailLat, courseDetailColor, userID, planName) "
			+ "select planDetailDate, planDetailLon, planDetailLat, planDetailColor, userID, planName "
			+ "from planDetail "
			+ "WHERE planName=#{planName} AND  userID=#{userID} ")
	int courseDetailshare(String userID, String planName);
	
	
}
