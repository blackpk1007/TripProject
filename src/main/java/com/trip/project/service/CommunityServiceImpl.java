package com.trip.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.CommunityDTO;
import com.trip.project.mapper.CommunityMapper;

@Service
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityMapper mapper;
	
	@Override
	public List<CommunityDTO> selectCommunity() {
		return mapper.selectCommunity();
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

}
