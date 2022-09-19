package com.nhathanh.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity(name = "NHANHANG")
@Table(name = "NHANHANG")
public class NhanHang implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ten_nhan_hang;
	@JsonIgnore
	@OneToMany(mappedBy = "nhanHang")
	List<DienThoai> dienThoai;

	public NhanHang(int id, String ten_nhan_hang) {
		super();
		this.id = id;
		this.ten_nhan_hang = ten_nhan_hang;
	}

	public NhanHang() {
		super();
	}
}
