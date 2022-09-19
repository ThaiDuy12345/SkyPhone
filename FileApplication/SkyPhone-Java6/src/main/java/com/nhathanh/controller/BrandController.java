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

import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.model.NhanHang;

@Controller
public class BrandController {
	@Autowired
	NhanHangDAO nhDao;
	int idEdit;

	@RequestMapping("/SkyPhone/pageAdmin/brandProduct")
	public String loadData(Model model) {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanHang> page = nhDao.findAll(pageable);
		model.addAttribute("items", page);
		NhanHang nh = new NhanHang();
		model.addAttribute("item", nh);
		return "pageAdmin/brandProduct";
	}

	// Phân trang nhãn hàng
	@RequestMapping("/SkyPhone/brand/page")
	public String paginate2(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanHang> ds = nhDao.findAll(pageable);
		model.addAttribute("items", ds);
		NhanHang nh = new NhanHang();
		model.addAttribute("item", nh);
		return "pageAdmin/brandProduct";
	}

	@RequestMapping("/brand/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		NhanHang item = nhDao.findById(id).get();
		model.addAttribute("item", item);
		// Reset lại trang bên bảng chứa nhãn hàng
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanHang> page = nhDao.findAll(pageable);
		model.addAttribute("items", page);
		idEdit = item.getId();
		return "pageAdmin/brandProduct";
	}

	@RequestMapping("/brand/create")
	public String create(Model model, NhanHang item) {
		nhDao.save(item);
		model.addAttribute("message", "Thêm mới nhãn hàng " + item.getTen_nhan_hang() + " thành công!");
		return "forward:/SkyPhone/pageAdmin/brandProduct";
	}

	@RequestMapping("/brand/update")
	public String update(Model model, NhanHang item) {
		NhanHang findNhanHang = nhDao.findById(idEdit).get();
		findNhanHang.setTen_nhan_hang(item.getTen_nhan_hang());
		nhDao.save(findNhanHang);
		model.addAttribute("message", "Cập nhật lại nhãn hàng " + findNhanHang.getTen_nhan_hang() + " thành công!");
		return "forward:/brand/edit/" + findNhanHang.getId();
	}
}
