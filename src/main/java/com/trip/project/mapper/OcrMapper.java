package com.trip.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.trip.project.dto.LoginDTO;
import com.trip.project.dto.OcrDTO;
import com.trip.project.dto.RecommandDTO;

@Mapper
public interface OcrMapper {
 
    
    @Select(" SELECT placeNumber,placeName, placeAddress FROM place WHERE placeNumber=#{placeNumber} " )
    OcrDTO selectByPlaceNumber(int placeNumber);
    
    @Select(" SELECT recommandUserID,recommandPlaceNumber, recommandDate FROM recommand WHERE recommandPlaceNumber=#{placeNumber} " )
    List<RecommandDTO> selectRecommandPlaceNumber(int placeNumber);
    
    @Insert("INSERT INTO recommand (recommandUserID, recommandPlaceNumber, recommandDate) VALUES (#{userID}, #{placeNumber}, #{date}) ")
    void insertRecommandData(String userID, int placeNumber, String date);
}
