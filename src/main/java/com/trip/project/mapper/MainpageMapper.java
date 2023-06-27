package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.MainpageDTO;

@Mapper
public interface MainpageMapper {
	@Select(" SELECT placeName FROM place ORDER BY placeGood DESC LIMIT 6 ")
	List<MainpageDTO> selectList();
	
	@Select(" SELECT placeName FROM place WHERE placeTag1='#반려동물' ORDER BY placeGood DESC LIMIT 4 ")
	List<MainpageDTO> animalList();
	
	@Select(" SELECT placeName FROM place WHERE placeTag1='#부모' ORDER BY placeGood DESC LIMIT 4 ")
	List<MainpageDTO> parentsList();
}
