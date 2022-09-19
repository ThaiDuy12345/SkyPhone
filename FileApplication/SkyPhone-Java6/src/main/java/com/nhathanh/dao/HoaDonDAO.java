package com.nhathanh.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nhathanh.model.HoaDon;

public interface HoaDonDAO extends JpaRepository<HoaDon, String> {
	@Query(value = "SELECT * FROM HoaDon hd where hd.sdt_nguoi_nhan like ?1", nativeQuery = true)
	public List<HoaDon> getHoaDonBySDT(String sdt_nguoi_nhan);

	@Query(value = "Select COUNT(*) From HoaDon hd where (hd.nguoi_thanh_toan like ?1 or hd.nguoi_thanh_toan like '' or hd.nguoi_thanh_toan is null) and hd.tinh_trang = 0", nativeQuery = true)
	public List<Integer> findWaitingCount(String name);

	@Query(value = "Select * From HoaDon hd where (hd.nguoi_thanh_toan like ?1 or hd.nguoi_thanh_toan like '') and (hd.tinh_trang = 2) order by hd.ngay_tao_don desc", nativeQuery = true)
	public List<HoaDon> findAllByNameAndDoneStatus(String name);

	@Query(value = "Select * From HoaDon hd where (hd.nguoi_thanh_toan like ?1 or hd.nguoi_thanh_toan like '' or hd.nguoi_thanh_toan is null) and (hd.tinh_trang = 1 or hd.tinh_trang = 0) order by hd.tinh_trang, hd.ngay_tao_don desc\n", nativeQuery = true)
	public List<HoaDon> findAllByNameAndNotDoneStatus(String name);

	@Query(value = "Insert into HoaDon (id_hd,so_luong_don,tong_gia,dia_chi_gui,ten_nguoi_nhan,sdt_nguoi_nhan"
			+ ",dia_chi_nhan,ngay_tao_don,tinh_trang)values(?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
	public void insertHoaDon(String idHd, int soLuong, double tongGia, String diaChiGui, String tenNn, String sdtNn,
			String diaCn, Date ngayTao, int tinhTrang);

	@Query(value = "SELECT * FROM HoaDon where sdt_nguoi_nhan like ?1", nativeQuery = true)
	List<HoaDon> findBySdt_nguoi_nhan(String sdt);

}
