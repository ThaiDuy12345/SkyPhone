package com.nhathanh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhathanh.model.DienThoai;

public interface DienThoaiDAO extends JpaRepository<DienThoai, String> {
	@Query(value = "Select * from DienThoai dt where dt.ten_dt like ?1", nativeQuery=true)
    public List<DienThoai> getDienThoaiByTen(String ten_dt);

	@Query(value = "INSERT INTO DienThoai(ten_dt,dung_luong,mau,tra_gop,nha_sx,gia,mo_ta,bao_hanh,hoat_dong,id_nhan_hang,so_luong) values"
			+ "(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)", nativeQuery = true)
	public void saveDienThoai(String tenDt, String dungLuong, String mau, String traGop, String nhaSx, double gia,
			String moTa, String baoHanh, boolean hoatDong, int idNh, int soLuong);

	@Query(value = "Select o from DIENTHOAI o where o.hoat_dong = ?1")
	public List<DienThoai> listDienThoaiDisplay(boolean hoatDong);
	
	@Query(value = "Select o from DIENTHOAI o where o.hoat_dong = ?1")
	public List<DienThoai> listAllSkyPhoneUntive(boolean hoatDong);

	@Query(value = "Select * from DIENTHOAI o where o.hoat_dong = 1 and o.id_nhan_hang like ?1",nativeQuery = true)
	public List<DienThoai> listDienThoaiWithBrand(int id_brand);

	@Query(value = "Select * from DIENTHOAI o where o.hoat_dong = ?2 and o.ten_dt like ?1",nativeQuery = true)
	public List<DienThoai> listDienThoaiDisplayByKeySearch(String keySearch, boolean hoat_dong);
}
