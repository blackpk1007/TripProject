package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.MainpageDTO;
import com.trip.project.dto.PlaceDTO;

@Mapper
public interface MainpageMapper {
	@Select(" SELECT placeName FROM place ORDER BY placeGood DESC LIMIT 6 ")
	List<MainpageDTO> selectList();
	
	@Select(" SELECT placeName FROM place WHERE placeTag1='#반려동물' ORDER BY placeGood DESC LIMIT 4 ")
	List<MainpageDTO> animalList();
	
	@Select(" SELECT placeName FROM place WHERE placeTag1='#부모' ORDER BY placeGood DESC LIMIT 4 ")
	List<MainpageDTO> parentsList();
	
	@Select("SELECT placeNumber, placeName, placeAddress, placeCategory, placePhone, placeGood, placeContent, placeInfo, placeTag1, placeTag2, placeTag3, placeLon, placeLat FROM place WHERE placeName = #{placeName}")
	PlaceDTO getPlaceInfo(@Param("placeName") String placeName);

}
