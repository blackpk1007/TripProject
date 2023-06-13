package com.trip.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.OcrDTO;

@Mapper
public interface OcrMapper {
	@Select(" SELECT * FROM place WHERE placeNumber=15 ")
	OcrDTO selectOne();
}
