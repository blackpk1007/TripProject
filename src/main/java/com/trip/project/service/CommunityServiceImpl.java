package com.trip.project.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.dto.ImageDTO;
import com.trip.project.dto.SearchDTO;
import com.trip.project.dto.UploadFile;
import com.trip.project.mapper.CommunityMapper;
import com.trip.project.paging.Pagination;
import com.trip.project.paging.PagingResponse;

@Service
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityMapper mapper;
	
	@Override
	public PagingResponse<CommunityDTO> selectCommunity(final SearchDTO params) {
		//조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
		int count = mapper.Count(params);
		
		if(count < 1) {
			return new PagingResponse<>(Collections.emptyList(),null);
		}
		
		//Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDTO 타입의 객체인 params에 계산된 페이지 정보 저장
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		System.out.println(pagination.getTotalRecordCount());
		
		//계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
		List<CommunityDTO> list = mapper.selectCommunityList(params);
		return new PagingResponse<>(list, pagination);
	}

	@Override
	public int insert(CommunityDTO dto) {
		
		return mapper.insert(dto);
	}

	@Override
	public CommunityDTO selectOne(int communityNumber) {
		return mapper.selectOne(communityNumber);
	}

	@Override
	public int update(CommunityDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int delete(int communityNumber) {
		return mapper.delete(communityNumber);
	}

	@Override
	public CommunityDTO ComunityselectOne() {
		return mapper.ComunityselectOne();
	}
	
	@Override 
	public int imageInsert(UploadFile image) {
		System.out.println("service : "+image);
		return mapper.imageInsert(image);
	}

	@Override
	public List<ImageDTO> selectImage() {
		return mapper.selectImage();
	}

	@Override
	public ImageDTO selectOneImg(int communityNumber) {
		return mapper.selectOneImg(communityNumber);
	}

	@Override
	public PagingResponse<CommunityDTO> selectCommunityTip(String communityCategory, SearchDTO params) {
		//조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
				
				int count = mapper.CountTip(params);
			
				
				if(count < 1) {
					return new PagingResponse<>(Collections.emptyList(),null);
				}
				
				//Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDTO 타입의 객체인 params에 계산된 페이지 정보 저장
				Pagination pagination = new Pagination(count, params);
				params.setPagination(pagination);
				
				System.out.println(pagination.getTotalRecordCount());
				
				//계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
				//params.setCommunityCategory(communityCategory);
				List<CommunityDTO> list = mapper.selectCommunityCategoryList(communityCategory,params);
				return new PagingResponse<>(list, pagination);
	}
	@Override
	public PagingResponse<CommunityDTO> selectCommunityReview(String communityCategory, SearchDTO params) {
		//조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
				
				int count = mapper.CountReview(params);
			
				
				if(count < 1) {
					return new PagingResponse<>(Collections.emptyList(),null);
				}
				
				//Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDTO 타입의 객체인 params에 계산된 페이지 정보 저장
				Pagination pagination = new Pagination(count, params);
				params.setPagination(pagination);
				
				System.out.println(pagination.getTotalRecordCount());
				
				//계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
				//params.setCommunityCategory(communityCategory);
				List<CommunityDTO> list = mapper.selectCommunityCategoryList(communityCategory,params);
				return new PagingResponse<>(list, pagination);
	}

	// 게시글 수 카운팅
	@Override
	public int Count(SearchDTO params) {
		return mapper.Count(params);
	}

	@Override
	public int updateImg(UploadFile image) {
		return mapper.updateImg(image);
	}

	@Override
	public List<CommunityDTO> usermainCommunity(String userID) {
		
		
		return mapper.usermainCommunity(userID);
	}
	//마이페이지에서 삭제 
	@Override
	public int communityDelete(String communityNumber, String userID) {

		return mapper.communityDelete(communityNumber, userID);
	}



}
