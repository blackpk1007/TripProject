package com.trip.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.OcrDTO;

@Mapper
public interface OcrMapper {
 
    
    @Select(" SELECT placeNumber,placeName, placeAddress FROM place WHERE placeNumber=#{placeNumber}")
    OcrDTO selectByPlaceNumber(int placeNumber);
}
