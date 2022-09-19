package com.nhathanh.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nhathanh.dao.DanhGiaDAO;
import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.dao.HDChiTietDAO;
import com.nhathanh.dao.HoaDonDAO;
import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.dao.TaiKhoanDAO;
import com.nhathanh.model.HoaDon;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartInfor;
import com.nhathanh.service.ShoppingCartService;

@CrossOrigin("*")
@RestController
public class ShoppingCartRestController {
	// -----------------------------------------Import
	// Beans----------------------------------------------
	@Autowired
	TaiKhoanDAO tkDAO;
	@Autowired
	DienThoaiDAO dtDAO;
	@Autowired
	HoaDonDAO hdDAO;
	@Autowired
	HDChiTietDAO hdctDAO;
	@Autowired
	DanhGiaDAO dgDAO;
	@Autowired
	NhanHangDAO nhDAO;
	@Autowired
	ShoppingCartService scs;
	@Autowired
	SessionService ss;

	// Thêm một sản phẩm vào giỏ hàng khi người dùng nhấn mua ngay
	@GetMapping("/cart/add/{id}")
	public List<ShoppingCartInfor> addItemToCart(@PathVariable("id") String id_dt, Model model) {
		// model.addAttribute("hoaDon",new HoaDon());
		scs.add(dtDAO.findById(id_dt).get());
		return scs.getList();
	}

	// ------------------------------------------------------------------------------//
	// Xóa sản phẩm khỏi giỏ hàng
	@DeleteMapping("/cart/delete/{id}")
	public void removeItem(@PathVariable("id") String id) {
		scs.remove(id);
	}
	// Remove all các sản phẩm có trong giỏ hàng
//	@DeleteMapping("/cart/removeAll")
//	public void removeAll() {
//		scs.removeAll();
//	}

}
