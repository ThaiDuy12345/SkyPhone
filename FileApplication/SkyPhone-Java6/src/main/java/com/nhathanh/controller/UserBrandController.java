package com.nhathanh.controller;

import com.nhathanh.dao.*;
import com.nhathanh.model.DienThoai;
import com.nhathanh.model.NhanHang;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartInfor;
import com.nhathanh.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Controller
public class UserBrandController {
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
    @GetMapping("/skyPhoneUser/ChooseBrand/{id}")
    public String getNhanHangToFilter(@PathVariable("id") int id){
        ss.set("brand", nhDAO.findById(id).get());
        return "redirect:/skyPhoneUser/brand";
    }

    @GetMapping("/skyPhoneUser/brand")
    public String getDisplay(Model model, @RequestParam("p") Optional<Integer> p) {
        NhanHang nh = (NhanHang) ss.get("brand");
        if(nh == null) return "redirect:/skyPhoneUser";
        List<DienThoai> page = dtDAO. listDienThoaiWithBrand(nh.getId());
        model.addAttribute("page", page);
        return "/pageUser/indexWithBrand";
    }
    // Siri:Đổ dữ liệu giỏ hàng lên thanh navbar
//    @ModelAttribute("cart")
//    public List<ShoppingCartInfor> getCart() {
//        return scs.getList();
//    }

    // Siri:Đổ dữ liệu nhãn hãng lên thanh navbar
    @ModelAttribute("brands")
    public List<NhanHang> getNhanHang() {
    	Optional<Integer> p = Optional.of(0);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
        return nhDAO.findAll(pageable).getContent(); 
    }
}
