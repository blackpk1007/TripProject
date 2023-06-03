package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.trip.project.dto.PlaceDTO;

@Mapper
public interface PlaceMapper {
	
	@Insert(" insert into place values(null, #{placeName}, #{placeAddress}, "
			+ "#{placeCategory}, #{placePhone}, #{placeGood}, #{placeContent}, "
			+ "#{placeInfo}, #{placeTag1}, #{placeTag2}, #{placeTag3}, #{placeLon}, #{placeLat}) ")
	int crawinsert(PlaceDTO dto);
	
}
