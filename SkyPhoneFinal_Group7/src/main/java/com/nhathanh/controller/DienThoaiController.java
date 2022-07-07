package com.nhathanh.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.model.DienThoai;
import com.nhathanh.model.NhanHang;

@Controller
public class DienThoaiController {
	// Phần này dùng để chỉnh sửa sản phẩm điện thoại
	@Autowired
	DienThoaiDAO dao;
	@Autowired
	NhanHangDAO daoNh;
	String idEdit;
	String pathImage = "src/main/webapp/images/phone_images";

	@RequestMapping("/SkyPhone/admin/dienThoai")
	public String getDienThoai(Model model, @ModelAttribute("phone") DienThoai phone) {
		List<NhanHang> nhanHang = daoNh.findAll();
		model.addAttribute("nhanHangList", nhanHang);
		model.addAttribute("sanPhamKoHinh", CheckHinh());
		return "pageAdmin/createProduct";
	}

	@RequestMapping("/dienthoai/edit/{id}")
	public String edit1(Model model, @PathVariable("id") String id) {
		// Tìm đến sản phẩm có id trùng với cơ sở dữ liệu
		DienThoai item = dao.findById(id).get();
		model.addAttribute("phone", item);
		idEdit = item.getId_dt();
		// Đổ lại nhãn hàng lên combobox
		List<NhanHang> nhanHang = daoNh.findAll();
		model.addAttribute("nhanHangList", nhanHang);
		// Hiển thị các sản phâm có thể cập nhật ảnh
		displayDienThoaiAnh(model);
		return "pageAdmin/updateProduct";
	}

	void displayDienThoaiAnh(Model model) {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		// ----------------------------------------//
		// Hiển thị sản phẩm ngừng bán có phân trang để cập nhật ảnh
		Page<DienThoai> ds = dao.listDienThoaiDisplay(false, pageable);
		model.addAttribute("sanPham", ds);
	}

//
	@RequestMapping("/dienthoai/create")
	public String create1(Model model, DienThoai item, @RequestParam("mota") String moTa,
			@RequestParam("nhanHang") int idNh) {
		try {
			model.addAttribute("message", "Đã thêm mới sản phẩm vào SkyPhone !");
			dao.saveDienThoai(item.getTen_dt(), item.getDung_luong(), item.getMau(), item.getTra_gop(),
					item.getNha_sx(), item.getGia(), moTa, item.getBao_hanh(), false, idNh, 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "forward:/SkyPhone/admin/dienThoai";
	}

	@RequestMapping("/dienthoai/update")
	public String update1(Model model, DienThoai item, @RequestParam("mota") String moTa,
			@RequestParam("nhanHang") Integer idNh) {
		item.setId_dt(idEdit);
		item.setMo_ta(moTa);
		// Id nhãn hàng
		item.setNhanHangID(daoNh.getOne(idNh));
		item.setHoat_dong(false);
		dao.save(item);
		model.addAttribute("message", "Cập nhật " + item.getTen_dt() + " thành công!");
		return "forward:/dienthoai/edit/" + item.getId_dt();
	}

	// Phân trang sản phẩm ngừng bán bán
	@RequestMapping("/SkyPhone/update/page")
	public String paginate2(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<DienThoai> ds = dao.listDienThoaiDisplay(false, pageable);
		model.addAttribute("sanPham", ds);
		// Đổ lại dữ liệu trên form
		DienThoai item = dao.findById(idEdit).get();
		model.addAttribute("phone", item);
		return "pageAdmin/updateProduct";
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

	// Hàm lấy số lượng file trong thư mục của sản phẩm trong SkyPhone
	List<Integer> soLuongAnh(List<DienThoai> ds) {
		// List chứa các số lượng của sản phẩm trong ảnh
		List<Integer> soLuong = new ArrayList<>();
		for (int i = 0; i < ds.size(); i++) {
			int slSkyPhone = soLuongAnh(ds.get(i).getId_dt());
			soLuong.add(slSkyPhone);
		}
		return soLuong;
	}

// Hàm trả về số lượng ảnh trong thư mục của ID sản phẩm trong SkyPhone
	Integer soLuongAnh(String idDienThoai) {
		File file = new File(pathImage + "/" + idDienThoai);
		String[] files = file.list();
		System.out.println(files.length);
		return files.length;
	}

}
