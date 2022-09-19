package com.nhathanh.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.model.DienThoai;

@CrossOrigin("*")
@RestController
public class ActiveSkyPhoneRestController {
	@Autowired
	DienThoaiDAO dao;
	//Load tất cả các sản phảm hoạt động của skyphone
	@GetMapping("/skyPhone/dienThoai/active")
	public List<DienThoai> skyPhoneActive() {
		// Hiển thị sản phẩm đang bán 
		List<DienThoai> dsDangBan = dao.listDienThoaiDisplay(true);
		return dsDangBan;
	}
	//Load tất cả các sản phảm ngừng hoạt động của skyphone
	@GetMapping("/skyPhone/dienThoai/unactive")
	public List<DienThoai> skyPhoneUnActive() {
		// Hiển thị sản phẩm đang bán
		List<DienThoai> dsDangBan = dao.listDienThoaiDisplay(false);
		return dsDangBan;
	}
	//Tạm dừng sản phẩm của SkyPhone
	@PutMapping("/skyPhone/admin/stop/{id}")
	public Boolean stopSkyPhone(@PathVariable("id") String id) {
		try {
			DienThoai dt = dao.findById(id).get();
			dt.setHoat_dong(false);
			dao.save(dt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//Tiếp tục hoạt động của sản phẩm SkyPhone
	@PutMapping("/skyPhone/admin/continue/{id}")
	public Boolean continueSkyPhone(@PathVariable("id") String id) {
		try {
			DienThoai dt = dao.findById(id).get();
			dt.setHoat_dong(true);
			dao.save(dt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
