package com.nhathanh.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("skyphone/GioHang")
	public String order(Model model) {
		if (scs.getList().size() == 0) {
			model.addAttribute("page", "/views/errorPage/notItem.jsp");
		} else {
			// Thêm các sản phẩm trong danh sách giỏ hàng
			model.addAttribute("cart", scs.getList());
			model.addAttribute("amount", scs.getAmoutInCart());
			model.addAttribute("page", "/views/pageUser/item.jsp");
		}
		return "pageUser/gioHang";
	}

	// Thêm một sản phẩm vào giỏ hàng khi người dùng nhấn nút
	@GetMapping("/cart/addFormCart/{id}")
	public String addItemToCartNew(Model model, @PathVariable("id") String id_dt) {
		DienThoai dt = dtDAO.findById(id_dt).get();
		scs.add(dt);
		return "redirect:/skyphone/GioHang";
	}

	// Xóa 1 sản phẩm khỏi giỏ hàng khi người dùng nhấn nút xóa hoặc giảm số lượng
	// đi
	@RequestMapping("/cart/remove/{id}")
	public String removeToCart(@PathVariable("id") String id) {
		scs.remove(id);
		return "redirect:/skyphone/GioHang";// hiển thị lại giỏ hàng
	}

	// Đổ dữ liệu nhãn hãng lên thanh navbar
	@ModelAttribute("brands")
	public List<NhanHang> getNhanHang() {
		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		return nhDAO.findAll(pageable).getContent();
	}
	//Xóa sản phẩm khỏi giỏ hàng
	@RequestMapping("/cart/removeItem/{id}")
	public String removeItemInCart(@PathVariable("id") String id) {
		for (int i = 0; i < scs.getList().size(); i++) {
			if(scs.getList().get(i).getDienThoai().getId_dt().equals(id)) {
				scs.getList().remove(i); 
			}
		}  
		System.out.println("Xóa sản phẩm mang ID: "+id+" thành công!");
		return "redirect:/skyphone/GioHang";// hiển thị lại giỏ hàng
	}
	// ------------------------------------------------------------------------------//
	// Đặt hàng
	@PostMapping("/skyPhone/order")
	public String bookProduct(Model model,@ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("tp") String tp,
			@RequestParam("quan") String quan, @RequestParam("phuong") String phuong,
			@RequestParam("diaChi") String diaChi, @RequestParam("yeuCauKhac") String orther) {
		// Thêm hóa đơn vào cơ sở dữ liệu

		// Hàm tạo mới id cho đơn hàng!
		String uniqueID = UUID.randomUUID().toString();
		hoaDon.setId_hd(uniqueID);
		hoaDon.setSo_luong_don(scs.getList().size());
		hoaDon.setTong_gia(scs.getAmoutInCart());
		String diaChiGui = tp + " " + quan + " " + phuong + " " + diaChi;
		hoaDon.setDia_chi_gui(diaChiGui);
		hoaDon.setDia_chi_nhan(diaChi + "- Yêu cầu khác: " + orther);
		try {
			hdDao.insertHoaDon(hoaDon.getId_hd(), hoaDon.getSo_luong_don(), hoaDon.getTong_gia(),
					hoaDon.getDia_chi_gui(), hoaDon.getTen_nguoi_nhan(), hoaDon.getSdt_nguoi_nhan(),
					hoaDon.getDia_chi_nhan(), hoaDon.getNgay_tao_don(), hoaDon.getTinh_trang());
			System.out.println("Thêm mới hóa đơn thành công!");
		} catch (Exception e) {

		}
		// Thêm hóa đơn chi tiết vào cơ sở dữ liệu
		try {
			insertHdct(hoaDon);
			System.out.println("Thêm mới hóa đơn chi tiết thành công!");
			model.addAttribute("message","Đặt hàng thành công bộ phận xử lý SkyPhone sẽ xử lý đơn hàng của bạn!");
			model.addAttribute("page", "/views/errorPage/notItem.jsp");
			//Xóa đi các sản phẩm trong giỏ hàng
			scs.getList().clear();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Lỗi gì đó thêm mới hóa đơn chi tiết thất bại!");
		}
		return "/pageUser/gioHang";
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
