//package com.nhathanh.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class AuthInterConfig implements WebMvcConfigurer {
//	@Autowired
//	AuthInterceptor auth;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(auth).addPathPatterns("/SkyPhone/index","/skyPhoneAdmin","/skyPhoneEmploy","/skyPhoneAdmin/**","/skyPhoneEmploy/**")
//				.excludePathPatterns("/login");
//	}
//}
