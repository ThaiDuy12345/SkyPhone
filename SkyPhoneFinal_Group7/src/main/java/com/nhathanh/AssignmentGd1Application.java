package com.nhathanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class AssignmentGd1Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(AssignmentGd1Application.class, args);
	}

//	@Configuration
//	public class WebConfig implements WebMvcConfigurer {
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
//					.setCachePeriod(0);
//		}
//	}
}
