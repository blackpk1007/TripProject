package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.PlaceDTO;

@Mapper
public interface PlaceMapper {
	
	@Insert(" insert into place values(null, #{placeName}, #{placeAddress}, "
			+ "#{placeCategory}, #{placePhone}, #{placeGood}, #{placeContent}, "
			+ "#{placeInfo}, #{placeTag1}, #{placeTag2}, #{placeTag3}, #{placeLon}, #{placeLat}) ")
	int crawinsert(PlaceDTO dto);
	
	// 모든 장소 좌표 마커
	@Select(" select * from place where placeCategory = #{placeCategory}" )
	List<PlaceDTO> placeCategoryMarker(String category);
	
	// 맛집 정보
	@Select(" select * from place where placeCategory = 'restaurant' order by placeGood desc ")
	List<PlaceDTO> placeRestaurantList();
}
