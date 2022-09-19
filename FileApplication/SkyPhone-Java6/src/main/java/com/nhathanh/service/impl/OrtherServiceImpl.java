//package com.nhathanh.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nhathanh.dao.HDChiTietDAO;
//import com.nhathanh.model.DienThoai;
//import com.nhathanh.service.OrderService;
//@Service
//public class OrtherServiceImpl implements OrderService {
//	@Autowired
//	HDChiTietDAO ddao;
//	
//	@Override
//	public List<DienThoai> create(JsonNode orderData) {
//		ObjectMapper mapper = new ObjectMapper();
//		TypeReference<List<DienThoai>> type = new TypeReference<List<DienThoai>>() {
//		};
//		List<DienThoai> details = mapper.convertValue(orderData.get("orderDetails"), type);
//		for (int i = 0; i <details.size(); i++) {
//			System.out.println(details.get(i).getTen_dt());
//		} 
//		return details;
//	}
//
//}
