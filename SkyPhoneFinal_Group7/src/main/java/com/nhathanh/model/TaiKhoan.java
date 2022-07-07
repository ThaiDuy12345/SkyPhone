package com.nhathanh.model;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Data
@Entity(name = "TAIKHOAN")
@Table(name = "TAIKHOAN")
public class TaiKhoan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "ho_ten")
	private String ho_ten;
	@Column(name = "sdt", unique = true)
	private String sdt;
	@Column(name = "cmnd", unique = true)
	private String cmnd;
	@Column(name = "vai_tro")
	private int vai_tro;
	@Column(name = "hinh")
	private String hinh;
}
