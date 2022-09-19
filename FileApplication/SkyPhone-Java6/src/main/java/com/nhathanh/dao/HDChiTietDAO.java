package com.nhathanh.dao;

import com.nhathanh.model.HDChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HDChiTietDAO extends JpaRepository<HDChiTiet, Integer> {
	@Query(value = "Select * from HDChiTiet hdct where hdct.id_hd like ?1", nativeQuery = true)
	List<HDChiTiet> findByIdHoaDon(String id_hd);

	@Modifying
	@Transactional
	@Query(value = "Delete from HDChiTiet where id_hd = ?1", nativeQuery = true)
	public void deleteAllWithHoaDon(String id);
	
	@Query(value="INSERT INTO HDChiTiet (so_luong_don,tong_gia_dct,id_hd,id_dt)values(?1,?2,?3,?4)", nativeQuery = true)
	public void insertHdct(int sl,double tongGia,String idHd,String idDt);
	
	@Query(value = "SELECT * FROM HoaDon hd, HDChiTiet hdct where hdct.sdt_nguoi_nhan like ?1 AND hd.id_hd = hdct.id_hd", nativeQuery = true)
	List<HDChiTiet> getHDCTByid(String id);

}
