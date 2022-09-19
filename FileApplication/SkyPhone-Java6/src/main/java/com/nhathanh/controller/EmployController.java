package com.nhathanh.controller;

import com.nhathanh.dao.*;
import com.nhathanh.model.DanhGia;
import com.nhathanh.model.HoaDon;
import com.nhathanh.model.TaiKhoan;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployController {
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

    // ----------------------------------------Employ
    // Comment-----------------------------
    @GetMapping("/skyPhoneEmploy/comment")
    public String getEmployComment(Model model) {
        return "/pageEmploy/staff_comment";
    }

    // ----------------------------------------Employ
    // Order-----------------------------

    @GetMapping("/skyPhoneEmploy/order")
    public String getEmployOrder(Model model) {
        TaiKhoan tk = (TaiKhoan) ss.get("user");
        List<HoaDon> doneStatusList = hdDAO.findAllByNameAndDoneStatus(tk.getHo_ten());
        List<HoaDon> notDoneStatusList = hdDAO.findAllByNameAndNotDoneStatus(tk.getHo_ten());
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
}
