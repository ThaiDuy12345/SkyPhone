package com.nhathanh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.model.HoaDon;
import com.nhathanh.model.NhanHang;

@Controller
public class ShoppingCartController {
	@Autowired
	NhanHangDAO nhDAO;

	@RequestMapping("skyphone/GioHang/{item}")
	public String order(Model model, @PathVariable("item") Integer soLuong) {
		if (soLuong == 0) {
			model.addAttribute("page", "/errorPage/notItem.html");
		} else {
			model.addAttribute("page", "/pageUser/item.html");
			model.addAttribute("hoaDon", new HoaDon());
		}
		return "pageUser/gioHang";
	}

	// Siri:Đổ dữ liệu nhãn hãng lên thanh navbar
	@ModelAttribute("brands")
	public List<NhanHang> getNhanHang() {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		return nhDAO.findAll(pageable).getContent();
	}
}
