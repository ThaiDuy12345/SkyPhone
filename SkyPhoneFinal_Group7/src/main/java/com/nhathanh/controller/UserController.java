package com.nhathanh.controller;

import com.nhathanh.dao.*;
import com.nhathanh.model.DanhGia;
import com.nhathanh.model.DienThoai;
import com.nhathanh.model.HDChiTiet;
import com.nhathanh.model.HoaDon;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class UserController {
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


}
