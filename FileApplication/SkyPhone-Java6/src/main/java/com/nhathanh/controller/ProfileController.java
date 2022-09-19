package com.nhathanh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhathanh.dao.HoaDonDAO;
import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.model.HoaDon;
import com.nhathanh.model.NhanHang;
import com.nhathanh.service.SessionService;

@Controller
public class ProfileController {
	@Autowired
	NhanHangDAO nhDAO;
	@Autowired
	SessionService session;
	@Autowired
	HoaDonDAO hdDAO;

	String sucsess = "";

	@RequestMapping("/history/proFile")
	String profilll(Model model) {
		model.addAttribute("page", "/views/pageUser/profile.jsp");
		model.addAttribute("profile", "active");
		String sdt = session.get("sdtHistory");
		List<HoaDon> HoaDon = hdDAO.getHoaDonBySDT(sdt);
		model.addAttribute("HoaDon", HoaDon);
		if (!sucsess.equals("")) {
			model.addAttribute("message", sucsess);
			sucsess = "";
		}
		return "/pageUser/historyOrder";
	}

	// Siri:Đổ dữ liệu nhãn hãng lên thanh navbar
	@ModelAttribute("brands")
	public List<NhanHang> getNhanHang() {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		return nhDAO.findAll(pageable).getContent();
	}

	@PostMapping("/SkyPhoneUser/update/profile")
	public String updateProfile(Model model, @RequestParam("tenNn") String tenNn, @RequestParam("tp") String tp,
			@RequestParam("quanHuyen") String quanHuyen, @RequestParam("phuongXa") String phuongXa,
			@RequestParam("soNha") String soNha) {
		List<HoaDon> hd = hdDAO.findBySdt_nguoi_nhan(session.get("sdtHistory"));
		String diaChi = "";
		diaChi = tp + " " + quanHuyen + " " + phuongXa + " " + soNha;
		if (diaChi.trim().equals("")) {
			diaChi = hd.get(0).getDia_chi_gui();
		}
		if (tenNn.trim().equals("")) {
			tenNn = hd.get(0).getTen_nguoi_nhan();
		}
		try {
			for (int i = 0; i < hd.size(); i++) {
				hd.get(i).setDia_chi_gui(diaChi);
				hd.get(i).setTen_nguoi_nhan(tenNn);
				hdDAO.save(hd.get(i));
			}
			sucsess = "Cập nhật profile của số điện thoại: " + session.get("sdtHistory") + " thành công!";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/history/proFile";
	}

	@RequestMapping("/SkyPhoneUser/logout")
	public String logoutUser() {
		session.set("sdtHistory", null);
		return "redirect:/skyPhoneUser";
	}
}
