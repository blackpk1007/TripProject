package com.trip.project.service;

import java.util.List;

import com.trip.project.dto.CommunityDTO;

public interface CommunityService {
	public List<CommunityDTO> selectCommunity();
	public int insert(CommunityDTO dto);
	public CommunityDTO selectOne(int communityNumber);
	public int update(CommunityDTO dto);
	public int delete(int communityNumber);
}
