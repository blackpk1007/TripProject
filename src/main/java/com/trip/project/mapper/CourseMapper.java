package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trip.project.dto.CourseDTO;
import com.trip.project.dto.CourseDetailDTO;

@Mapper
public interface CourseMapper {
	
	@Select(" select * from course order by courseFirstDate desc ")
	List<CourseDTO> courseDateSort();
	
	@Select(" select * from course order by courseCount desc ")
	List<CourseDTO> courseCountSort();
	
	@Select(" select * from course order by courseFirstDate desc ")
	List<CourseDTO> courseList();
	
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
	
	@Select(" select * from courseDetail where userID = #{userID} and planName = #{planName} ")
	List<CourseDetailDTO> courseDetailList(String userID, String planName);
	
	@Update(" update course set courseCount = courseCount + 1 where userID = #{userID} and planName = #{planName} ")
	int courseListCount(String userID, String planName);
}
