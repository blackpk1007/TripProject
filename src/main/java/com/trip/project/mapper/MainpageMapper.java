package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.MainpageDTO;

@Mapper
public interface MainpageMapper {
	@Select("SELECT placeName FROM place ORDER BY placeGood DESC LIMIT 6 ")
	List<MainpageDTO> selectList();
}
