package com.nhathanh.controller;

import com.nhathanh.dao.*;
import com.nhathanh.model.*;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartInfor;
import com.nhathanh.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

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

	// -----------------------------------------GET Mapping
	// Method--------------------------------------------
	// -----------------------------------------User
	// URL--------------------------------------------
	@GetMapping("/skyPhoneUser")
	public String getLink1(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam(name = "KeySearch", defaultValue = "Nothing_To_Search_Here") String keySearch) {
		System.out.println("KeySearch is: " + keySearch);
		Pageable pageable;
		if (!keySearch.trim().equals("Nothing_To_Search_Here")) {
			List<DienThoai> page = dtDAO.listDienThoaiDisplayByKeySearch("%" + keySearch + "%", true);
			model.addAttribute("page", page);
			model.addAttribute("keySearch", 1);
		} else {
			pageable = PageRequest.of(p.orElse(0), 10);
			List<DienThoai> page = dtDAO.listDienThoaiDisplay(true);
			model.addAttribute("page", page);
		}
		return "/pageUser/index";

	}

	// -----------------------------------------Admin
	// URL--------------------------------------------
	@GetMapping("/skyPhoneAdmin")
	public String getAdmin() {
		return "/pageAdmin/indexAdmin";
	}

	// -----------------------------------------Employee
	// URL--------------------------------------------
	@GetMapping("/skyPhoneEmploy")
	public String getEmploy(Model model) {
		TaiKhoan tk = (TaiKhoan) ss.get("user");
		model.addAttribute("countOrder", hdDAO.findWaitingCount(tk.getHo_ten()).get(0));
		model.addAttribute("countComment", dgDAO.findWaitingCount().get(0));
		return "/pageEmploy/staff_main";
	}

	// -------------------------------User URL---------------------------------
	// Siri:Đổ dữ liệu chi tiết một sản phẩm khi chọn sản phẩm chi tiết
	@RequestMapping("/item/product/{id}")
	public String getDetailProduct(Model model, @PathVariable("id") String id) {
		DienThoai dt = dtDAO.findById(id).get();
		// Dữ liệu null thì gọi trang error
		if (dt.getId_dt().equals("") || dt.getId_dt() == null) {
			return "/errorPage/error404";
		} else {
			List<DanhGia> danhGia = dgDAO.findAllDanhGiaWithDienThoai("%" + dt.getId_dt() + "%");
			List<DienThoai> dungLuongVaMau = dtDAO.getDienThoaiByTen("%" + dt.getTen_dt() + "%");
			// AddAttribute Sản phẩm chi tiết, màu và dung lượng
			model.addAttribute("DanhGia", danhGia);
			model.addAttribute("detailItem", dt);
			model.addAttribute("dungLuongVaMau", dungLuongVaMau);
			model.addAttribute("amount", scs.getAmoutInCart());
			return "/pageUser/detailProduct";
		}
	}

	// -----------------------------------------Model
	// Attribute-----------------------------------------------
	// Siri:Đổ dữ liệu giỏ hàng lên thanh navbar
	@ModelAttribute("cart")
	public List<ShoppingCartInfor> getCart() {
		return scs.getList();
	}

	// Siri:Đổ dữ liệu nhãn hãng lên thanh navbar
	@ModelAttribute("brands")
	public List<NhanHang> getNhanHang() {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		return nhDAO.findAll(pageable).getContent();
	}
	
	@GetMapping("/user/history")
	public String checkHistoryOrder(Model model, @RequestParam("id") Optional<String> sdt_hd) {
		String sdt = sdt_hd.orElse(ss.get("id"));
		if (sdt_hd.orElse(ss.get("id")).equals("") || sdt_hd.orElse(ss.get("id")) == null) {
			return "/errorPage/error404";
		} else {
			List<HoaDon> HoaDon = hdDAO.getHoaDonBySDT(sdt);
			ss.set("sdtHistory", sdt);
			model.addAttribute("list", "active");
			model.addAttribute("page", "/pageUser/history.html");
			model.addAttribute("HD", HoaDon);
			return "/pageUser/historyOrder";
		}
	}

}
