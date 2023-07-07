package com.trip.project.dto;

import lombok.Data;

@Data
public class placePagination {
	
	private int pageNum;	//페이지 번호
	
	private int size;	//한 페이지당 출력 DATA 개수

	public placePagination(){
		this(1,20);
	}
	
	public placePagination(int pageNum,int size) {
		this.pageNum=pageNum;
		this.size=size;
	}
	
	public int getSkip() {
		return this.pageNum = (pageNum-1) * size;
	}
    
    //이 getSkip을 통해 mapper.xml에서 ${skip}을 쓸 수 있습니다.
}
