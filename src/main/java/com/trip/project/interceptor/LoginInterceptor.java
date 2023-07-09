package com.trip.project.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	
	// controller 실행 요청 전에 수행되는 메소드.
	// return false일 경우 controller 실행x
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		if(request.getRequestURI().equals("/login") ||
		   
		   request.getSession().getAttribute("login") != null){ 
			return true;
		}
		
		if(request.getRequestURI().startsWith("/community/communitymain") ||
		   request.getRequestURI().startsWith("/chatting") ||
		   request.getRequestURI().startsWith("/course/recommandcourse") ||
		   request.getSession().getAttribute("login") == null) {
			
			response.sendRedirect("/login");
			
			return false;
		}
		return false;
	}
	
	// view단으로 forward 되기 전에 수행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	}
	
	// view까지 처리 끝난 후 실행
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
	}
}
