package com.nhathanh.controller;

import com.nhathanh.dao.*;
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/java/com/nhathanh/controller/MainController.java
import com.nhathanh.model.*;
=======
import com.nhathanh.model.DanhGia;
import com.nhathanh.model.DienThoai;
import com.nhathanh.model.HDChiTiet;
import com.nhathanh.model.HoaDon;
import com.nhathanh.model.NhanHang;
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/java/com/nhathanh/controller/MainController.java
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartInfor;
import com.nhathanh.service.ShoppingCartService;

<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/java/com/nhathanh/controller/MainController.java
=======

>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/java/com/nhathanh/controller/MainController.java
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
<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/java/com/nhathanh/controller/MainController.java
	public String getLink1(Model model, @RequestParam("p") Optional<Integer> p, @RequestParam(name="KeySearch", defaultValue="Nothing_To_Search_Here") String keySearch) {
		System.out.println("KeySearch is: "+keySearch);
		Pageable pageable;
		if(!keySearch.trim().equals("Nothing_To_Search_Here")){
			pageable = PageRequest.of(0, Integer.MAX_VALUE);
			Page<DienThoai> page = dtDAO.listDienThoaiDisplayByKeySearch("%"+keySearch+"%", true, pageable);
			model.addAttribute("page", page);
			model.addAttribute("keySearch", 1);
		}else{
			pageable = PageRequest.of(p.orElse(0), 10);
			Page<DienThoai> page = dtDAO.listDienThoaiDisplay(true, pageable);
			model.addAttribute("page", page);
=======
	public String getLink1(Model model, @RequestParam("p") Optional<Integer> p) {
//		Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 12);
//		List<DienThoai> dt = dtDAO.findAll();
		Page<DienThoai> page = dtDAO.listDienThoaiDisplay(false, pageable);
//		model.addAttribute("item", dt);
		model.addAttribute("page", page);
		return "/pageUser/index";
	}

	// Siri:Đổ dữ liệu chi tiết một sản phẩm khi chọn sản phẩm chi tiết
	@GetMapping("/item/product/{id}")
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
			return "/pageUser/detailProduct";
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/java/com/nhathanh/controller/MainController.java
		}
		return "/pageUser/index";

<<<<<<< HEAD:SkyPhoneFinal_Group7/src/main/java/com/nhathanh/controller/MainController.java
=======
	// Siri:Kiểm tra lịch sử đơn hàng
	@GetMapping("/user/history")
	public String checkHistoryOrder(Model model, @RequestParam("id") Optional<String> sdt_hd) {
		String sdt = sdt_hd.orElse(ss.get("id"));
//		HDChiTiet hdct= hdctDAO.findById(sdt).get();
//		Dữ liệu null thì gọi trang error
		if (sdt_hd.orElse(ss.get("id")).equals("") || sdt_hd.orElse(ss.get("id")) == null) {
			return "/errorPage/error404";
		} else {
			List<HoaDon> HoaDon = hdDAO.getHoaDonBySDT(sdt);
			List<HoaDon> HoaDonName = hdDAO.getHDbyName(sdt);
			List<HDChiTiet> hdct = hdctDAO.getHDCTByid(sdt);
//					AddAttribute lịch sử mua hàng
			model.addAttribute("HDN", HoaDonName);
			model.addAttribute("HD", HoaDon);
			model.addAttribute("HDCT", hdct);
			return "/pageUser/historyOrder";
		}
	}

	// Siri:Thêm một sản phẩm vào giỏ hàng khi người dùng nhấn mua ngay
	@GetMapping("/cart/add/{id}")
	public String addItemToCart(Model model, @PathVariable("id") String id_dt) {
		DienThoai dt = dtDAO.findById(id_dt).get();
		scs.add(dt);
		return "redirect:/item/product/" + id_dt;
>>>>>>> b84b61f0bb5fcddc3ccff7762ecf5865deda4d69:Assignment_GD1/src/main/java/com/nhathanh/controller/MainController.java
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

	// ----------------------------------------Employ
	// Comment-----------------------------
	@GetMapping("/skyPhoneEmploy/comment")
	public String getEmployComment(Model model) {
		Pageable page = PageRequest.of(0, 5);
		Page<DanhGia> waitingCommentList = dgDAO.findWaitingComment(page);
		model.addAttribute("danhGia", waitingCommentList);
		return "/pageEmploy/staff_comment";
	}

	// Nhân viên: phân trang đánh giá
	@GetMapping("/skyPhoneEmploy/comment?page{id}")
	public String getEmployCommentPage(@PathVariable("id") int id, Model model) {
		Pageable page = PageRequest.of(id, 5);
		Page<DanhGia> waitingCommentList = dgDAO.findWaitingComment(page);
		model.addAttribute("danhGia", waitingCommentList);
		return "/pageEmploy/staff_comment";
	}

	// Nhân viên: duyệt một đánh giá
	@PostMapping("/skyPhoneEmploy/comment/accept/{stt}")
	public String postEmployAcceptComment(@PathVariable("stt") int id) {
		dgDAO.acceptById(id);
		return "redirect:/skyPhoneEmploy/comment";
	}

	// Nhân viên: xoá một đánh giá
	@PostMapping("/skyPhoneEmploy/comment/delete/{stt}")
	public String postEmployDeleteComment(@PathVariable("stt") int id) {
		dgDAO.deleteById(id);
		return "redirect:/skyPhoneEmploy/comment";
	}
	// ----------------------------------------Employ
	// Order-----------------------------

	@GetMapping("/skyPhoneEmploy/order")
	public String getEmployOrder(Model model) {
		Pageable page = PageRequest.of(0, 5);
		TaiKhoan tk = (TaiKhoan) ss.get("user");
		Page<HoaDon> doneStatusList = hdDAO.findAllByNameAndDoneStatus(tk.getHo_ten(), page);
		Page<HoaDon> notDoneStatusList = hdDAO.findAllByNameAndNotDoneStatus(tk.getHo_ten(), page);
		model.addAttribute("doneOrder", doneStatusList);
		model.addAttribute("notDoneOrder", notDoneStatusList);
		return "/pageEmploy/staff_order";
	}

	// Nhân viên: hoá đơn chi tiết
	@GetMapping("/skyPhoneEmploy/order/{id}")
	public String getEmployOrder(Model model, @PathVariable("id") String id_hd) {
		HoaDon hd = hdDAO.findById(id_hd).get();
		model.addAttribute("HoaDon", hd);
		model.addAttribute("HoaDonChiTiet", hdctDAO.findByIdHoaDon(hd.getId_hd()));
		if (hd.getTinh_trang() == 1) {
			return "/pageEmploy/staff_order_processing";
		} else if (hd.getTinh_trang() == 2) {
			return "/pageEmploy/staff_order_done";
		} else {
			return "/pageEmploy/staff_order_waiting";
		}
	}

	// Nhân viên: phân trang trong hoá đơn
	@GetMapping("/skyPhoneEmploy/order?page{id1}_{id2}")
	public String getPageEmployOrder(Model model, @PathVariable("id1") int doneOrderPage,
			@PathVariable("id2") int notDoneOrderPage) {
		Pageable donePage = PageRequest.of(doneOrderPage, 5);
		Pageable notDonePage = PageRequest.of(notDoneOrderPage, 5);
		TaiKhoan tk = (TaiKhoan) ss.get("user");
		Page<HoaDon> doneStatusList = hdDAO.findAllByNameAndDoneStatus(tk.getHo_ten(), donePage);
		Page<HoaDon> notDoneStatusList = hdDAO.findAllByNameAndNotDoneStatus(tk.getHo_ten(), notDonePage);
		model.addAttribute("doneOrder", doneStatusList);
		model.addAttribute("notDoneOrder", notDoneStatusList);
		return "/pageEmploy/staff_order";
	}

	// Nhân viên: Chuyển trạng thái của một hoá đơn
	@PostMapping("/skyPhoneEmploy/order/accept/{id}")
	public String postEmployAcceptOrder(@PathVariable("id") String id_hd) {
		TaiKhoan tk = (TaiKhoan) ss.get("user");
		HoaDon hd = hdDAO.findById(id_hd).get();
		hd.setTinh_trang(hd.getTinh_trang() + 1);
		hd.setNguoi_thanh_toan(tk.getHo_ten());
		hdDAO.save(hd);
		return "redirect:/skyPhoneEmploy/order/" + hd.getId_hd();
	}

	// Nhân viên: Xoá một hoá đơn
	@PostMapping("/skyPhoneEmploy/order/delete/{id}")
	public String postEmployDeleteOrder(@PathVariable("id") String id_hd) {
		HoaDon hd = hdDAO.findById(id_hd).get();
		hdctDAO.deleteAllWithHoaDon(hd.getId_hd());
		hdDAO.delete(hd);
		return "redirect:/skyPhoneEmploy/order";
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

	// Thêm một sản phẩm vào giỏ hàng khi người dùng nhấn mua ngay
	@GetMapping("/cart/add/{id}")
	public String addItemToCart(Model model, @PathVariable("id") String id_dt) {
		DienThoai dt = dtDAO.findById(id_dt).get();
		scs.add(dt);
		return "redirect:/item/product/" + id_dt;
	}

	// Siri:Insert một đánh giá
	@PostMapping("/item/product/addComment/{id}")
	public String insertCommentProduct(@PathVariable("id") String id, @RequestParam("soDienThoai") String sdt,
			@RequestParam("noiDung") String comment, Model model) {
		boolean check = false;
		List<HoaDon> list = hdDAO.findAll();
		for (HoaDon i : list) {
			if (i.getSdt_nguoi_nhan().equals(sdt)&& i.getTinh_trang()==2) {
				for (HDChiTiet j : i.getHDChiTiet() ) {
					if (j.getDienThoai().getId_dt().equals(id)) {
						check = true;
						break;
					}
				}
			}
		}
		if (check) {
			dgDAO.addDanhGia(sdt, comment, 0, id);
			model.addAttribute("message", "Đánh giá thành công, xin vui lòng chờ xét duyệt");
		} else {
			model.addAttribute("message", "Đánh giá thất bại, bạn chưa mua sản phẩm này");
		}
		return "forward:/item/product/" + id;
	}

	// Kiểm tra lịch sử đơn hàng
	@GetMapping("/user/history")
	public String checkHistoryOrder(Model model, @RequestParam("id") Optional<String> sdt_hd) {
		String sdt = sdt_hd.orElse(ss.get("id"));
//		Dữ liệu null thì gọi trang error
		if (sdt_hd.orElse(ss.get("id")).equals("") || sdt_hd.orElse(ss.get("id")) == null) {
			return "/errorPage/error404";
		} else {
			List<HoaDon> HoaDon = hdDAO.getHoaDonBySDT(sdt);
			//Lưu trữ số điện thoại vào Session
			ss.set("sdtHistory", sdt);
			model.addAttribute("list", "active");
			model.addAttribute("page", "/views/pageUser/history.jsp");
//			AddAttribute lịch sử mua hàng
			model.addAttribute("HD", HoaDon);
			return "/pageUser/historyOrder";
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
}
