package com.nhathanh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity(name = "HDCHITIET")
@Table(name = "HDCHITIET")
public class HDChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stt;
    
    private int so_luong_don;
    private double tong_gia_dct;
    @JsonIgnore
    @ManyToOne @JoinColumn(name="id_hd")
    private HoaDon hoaDon;
    @JsonIgnore
    @ManyToOne @JoinColumn(name="id_dt")
    private DienThoai dienThoai;
}
