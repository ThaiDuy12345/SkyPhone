package com.nhathanh.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.dao.HDChiTietDAO;
import com.nhathanh.dao.HoaDonDAO;
import com.nhathanh.dao.NhanHangDAO;
import com.nhathanh.model.DienThoai;
import com.nhathanh.model.HDChiTiet;
import com.nhathanh.model.HoaDon;
import com.nhathanh.model.NhanHang;
//import com.nhathanh.service.OrderService;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartService;

@Controller
public class OrtherController {
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
//	@Autowired
//	OrderService ortherService;

	// Đổ dữ liệu nhãn hãng lên thanh navbar
	@ModelAttribute("brands")
	public List<NhanHang> getNhanHang() {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		return nhDAO.findAll(pageable).getContent();
	}

//	// Đặt hàng
	@RequestMapping("/skyPhone/order")
	public String bookProduct(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("tp") String tp,
			@RequestParam("quan") String quan, @RequestParam("phuong") String phuong,
			@RequestParam("diaChi") String diaChi, @RequestParam("yeuCauKhac") String orther,
			@RequestParam("tongGia") Double tongGia, @RequestParam("size") Integer size) {

		// Thêm hóa đơn vào cơ sở dữ liệu
		// Hàm tạo mới id cho đơn hàng!
		String uniqueID = UUID.randomUUID().toString();
		hoaDon.setId_hd(uniqueID);

		hoaDon.setSo_luong_don(size);
		hoaDon.setTong_gia(tongGia);

		String diaChiGui = tp + " " + quan + " " + phuong + " " + diaChi;
		hoaDon.setDia_chi_gui(diaChiGui);
		hoaDon.setDia_chi_nhan(diaChi + " - Yêu cầu khác: " + orther);
		try {
			hdDao.insertHoaDon(hoaDon.getId_hd(), hoaDon.getSo_luong_don(), hoaDon.getTong_gia(),
					hoaDon.getDia_chi_gui(), hoaDon.getTen_nguoi_nhan(), hoaDon.getSdt_nguoi_nhan(),
					hoaDon.getDia_chi_nhan(), hoaDon.getNgay_tao_don(), hoaDon.getTinh_trang());
		} catch (Exception e) {
			System.out.println(e);
		}
		// Thêm hóa đơn chi tiết vào cơ sở dữ liệu
		try {
			System.out.println("Thêm mới hóa đơn chi tiết thành công!");
			model.addAttribute("message", "Đặt hàng thành công bộ phận xử lý SkyPhone sẽ xử lý đơn hàng của bạn!");
			model.addAttribute("page", "/errorPage/notItem.html");
			insertHdct(hoaDon);
			scs.removeAll();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Lỗi gì đó thêm mới hóa đơn chi tiết thất bại!");
		}
		return "pageUser/gioHang";
	}

	void insertHdct(HoaDon hd) {
		for (int i = 0; i < scs.getList().size(); i++) {
			HDChiTiet hdct = new HDChiTiet();
			// Số lượng của sản phẩm được đặt mua
			hdct.setSo_luong_don(scs.getList().get(i).getSoLuong());
			// Tổng giá tiền của sản phẩm được mua (số lượng nhân với giá sản phẩm)
			hdct.setTong_gia_dct(scs.getList().get(i).getSoLuong() * scs.getList().get(i).getDienThoai().getGia());
			// Truyền vào điện thoại được đặt mua
			hdct.setDienThoai(scs.getList().get(i).getDienThoai());
			hdct.setHoaDon(hd);
			hdctDao.save(hdct);
		}
	}

}
