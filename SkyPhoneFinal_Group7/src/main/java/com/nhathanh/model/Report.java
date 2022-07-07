package com.nhathanh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Report implements Serializable {
	@Id
	@Column(name = "sdt_nguoi_nhan")
	private String sdtNguoiNhan;
	@Column(name = "ten_nguoi_nhan")
	private String tenNguoiNhan;
	@Column(name = "tong_gia")
	private double tongGia;
	@Column(name = "luot_mua")
	private int luotMua;
	@Column(name = "tong_so_don_hang_da_mua")
	private int tongDonDaMua;
}
