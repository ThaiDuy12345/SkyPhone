package com.nhathanh.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.dao.HDChiTietDAO;
import com.nhathanh.dao.HoaDonDAO;
import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartInfor;
import com.nhathanh.service.ShoppingCartService;

@CrossOrigin("*")
@RestController
public class OrtherRestController {

	@Autowired
	ShoppingCartService scs;
	@Autowired
	NhanHangDAO nhDAO;
	@Autowired
	SessionService ss;
	@Autowired
	DienThoaiDAO dtDAO;
	@Autowired
	HoaDonDAO hdDao;
	@Autowired
	HDChiTietDAO hdctDao;

	@PostMapping("/cart/data")
	public List<ShoppingCartInfor> dataCart(@RequestBody JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		ShoppingCartInfor ds = mapper.convertValue(orderData, ShoppingCartInfor.class);

		TypeReference<List<ShoppingCartInfor>> type = new TypeReference<List<ShoppingCartInfor>>() {
		};
		List<ShoppingCartInfor> details = mapper.convertValue(orderData.get("dienThoai"), type).stream().peek(d -> {
			d.setDienThoai(ds.getDienThoai());
		}).collect(Collectors.toList());		
		for (int i = 0; i < details.size(); i++) {
			System.out.println(details.get(i).getDienThoai().getTen_dt()); 
		}
		return details;
	}
}
