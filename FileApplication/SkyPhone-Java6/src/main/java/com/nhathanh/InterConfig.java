package com.nhathanh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nhathanh.interceptor.AuthInterceptor;

@Configuration
public class InterConfig implements WebMvcConfigurer {
//
//	@Autowired
//	AuthInterceptor auth;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(auth).addPathPatterns("/skyPhoneAdmin")
//				.excludePathPatterns("login");
//	}
}