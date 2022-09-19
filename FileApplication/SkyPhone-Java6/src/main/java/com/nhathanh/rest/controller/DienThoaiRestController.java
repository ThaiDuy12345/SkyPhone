package com.nhathanh.rest.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.model.DienThoai;
import com.nhathanh.model.NhanHang;

@CrossOrigin("*")
@RestController
public class DienThoaiRestController {
	@Autowired
	NhanHangDAO daoNh;
	@Autowired
	DienThoaiDAO dao;
	String pathImage = "src/main/webapp/images/phone_images";

	@GetMapping("/nhanHang/all")
	public List<NhanHang> nhanHangAll() {
		return daoNh.findAll();
	}

	@GetMapping("/skyPhone/notImage")
	public List<DienThoai> skyPhoneNotImage() {
		return CheckHinh();
	}

	@PostMapping("/skyPhone/create")
	public Boolean createSkyPhone(@RequestBody DienThoai item) {
		try {
			dao.saveDienThoai(item.getTen_dt(), item.getDung_luong(), item.getMau(), item.getTra_gop(),
					item.getNha_sx(), item.getGia(), item.getMo_ta(), item.getBao_hanh(), item.getHoat_dong(),
					item.getNhanHang().getId(), 1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Hàm lấy toàn bộ sản phẩm chưa có hình và đã ngừng bán
	public List<DienThoai> CheckHinh() {
		// Lấy ra toàn bộ sản phẩm trong SkyPhone
		List<DienThoai> ds = dao.listAllSkyPhoneUntive(false);
		// Kiểm tra xem thư mục này đã tồn tại hay chưa
		for (int i = 0; i < ds.size(); i++) {
			// Lấy ra id của điện thoại xem thử thư mục có tồn tại hay chưa
			// Nếu chưa thì sản phẩm đó sẽ được xét vào danh sách sản phẩm chưa có hình
			File f = new File(pathImage + "/" + ds.get(i).getId_dt());
			if (!f.exists()) {
				ds.remove(i);
			}
		}
		return ds;
	}

}
