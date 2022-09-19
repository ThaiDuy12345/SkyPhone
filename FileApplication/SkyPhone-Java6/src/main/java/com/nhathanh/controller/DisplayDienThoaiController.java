package com.nhathanh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.model.DienThoai;

@Controller
public class DisplayDienThoaiController {
	@Autowired
	DienThoaiDAO dao;

	@RequestMapping("/SkyPhone/index")
	public String display() {
		return "/pageAdmin/indexAdmin";
	}

	// Phân trang sản phẩm đang bán
	@RequestMapping("/SkyPhone/page1")
	public String paginate1(Model model, @RequestParam("p") Optional<Integer> p) {
		List<DienThoai> dsDangBan = dao.listDienThoaiDisplay(true);
		model.addAttribute("itemAc", dsDangBan);
		// Hiển thị lại sản phẩm ngừng bán
		List<DienThoai> dsNgungBan = dao.listDienThoaiDisplay(false);
		model.addAttribute("itemUnActive", dsNgungBan);
		return "pageAdmin/indexAdmin";
	}

	// Phân trang sản phẩm
	@RequestMapping("/SkyPhone/page2")
	public String paginate2(Model model, @RequestParam("p") Optional<Integer> p) {
		// -------------------//
		// Hiển thị sản phẩm ngừng bán của SkyPhone
		List<DienThoai> dsNgungBan = dao.listDienThoaiDisplay(false);
		model.addAttribute("itemUnActive", dsNgungBan);
		// Hiển thị lại sản phẩm đang bán của SkyPhone
		List<DienThoai> dsDangBan = dao.listDienThoaiDisplay(true);
		model.addAttribute("itemAc", dsDangBan);
		return "pageAdmin/indexAdmin";
	}
}
