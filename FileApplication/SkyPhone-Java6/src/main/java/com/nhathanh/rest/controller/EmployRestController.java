package com.nhathanh.rest.controller;

import com.nhathanh.dao.*;
import com.nhathanh.model.*;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/skyPhoneEmploy")
public class EmployRestController {
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
    @Autowired
    ServletContext app;
    //Lấy hết đơn hàng đã hoàn thành
    @GetMapping("/order/getAllDoneOrder")
    public Map<String, Object> getAllDoneOrder(){
        TaiKhoan tk = (TaiKhoan) ss.get("user");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("hd", hdDAO.findAllByNameAndDoneStatus(tk.getHo_ten()));
        return result;
    }
    //Lấy hết đơn hàng đã chưa hoàn thành
    @GetMapping("/order/getAllNotDoneOrder")
    public Map<String, Object> getAllNotDoneOrder(){
        TaiKhoan tk = (TaiKhoan) ss.get("user");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("hd", hdDAO.findAllByNameAndNotDoneStatus(tk.getHo_ten()));
        return result;
    }
    //Lấy hết đánh giá
    @GetMapping("/comment/getAllComment")
    public Map<String, Object> getAllComment(){
        List<DanhGia> object = dgDAO.findWaitingComment();
        return this.mapping(object);
    }
    //Xoá 1 đánh giá
    @DeleteMapping("/comment/delete/{stt}")
    public void postEmployDeleteComment(@PathVariable("stt") int id) {
        dgDAO.deleteById(id);
    }
    //Cập nhật và phê duyệt 1 đánh giá
    @PutMapping("/comment/accept/{stt}")
    public void postEmployAcceptComment(@PathVariable("stt") int id) {
        dgDAO.acceptById(id);
    }
    //Chuyển Entity về dạng HashMap
    private Map<String, Object> mapping(List<DanhGia> list){
        Map<String, Object> object = new HashMap<>();
        List<DienThoai> dt = new ArrayList<>();
        List<NhanHang> nh = new ArrayList<>();
        object.put("dg", list);
        list.forEach(dg -> {
            dt.add(dg.getDienThoai());
            nh.add(dg.getDienThoai().getNhanHang());
        });
        object.put("dt", dt);
        object.put("nh", nh);
        return object;
    }

}
