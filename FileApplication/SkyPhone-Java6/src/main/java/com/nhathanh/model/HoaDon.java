package com.nhathanh.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HOADON")
@Table(name = "HOADON")
public class HoaDon {
	@Id
    private String id_hd;
    private String nguoi_thanh_toan;
    private int so_luong_don;
    private double tong_gia;
    private String dia_chi_gui;
    private String ten_nguoi_nhan;
    @Column(name="sdt_nguoi_nhan")
    private String sdt_nguoi_nhan;
    private String dia_chi_nhan;
    private Date ngay_tao_don = new Date();
    private int tinh_trang=0;
    @OneToMany(mappedBy = "hoaDon")
    private List<HDChiTiet> hDChiTiet;

}
