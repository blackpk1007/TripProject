package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.PlaceDTO;
import com.trip.project.dto.PlacePageDTO;

@Mapper
public interface PlacePageMapper {
	
	@Select(" select * from place order by placeNumber desc LIMIT 1, 10 ")
	List<PlaceDTO> findAll(PlacePageDTO params);
	
	@Select(" select count(*) from place ")
	int count(PlacePageDTO params);
}
