package com.nhathanh.dao;

import com.nhathanh.model.DanhGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DanhGiaDAO extends JpaRepository<DanhGia, String> {
    @Query(value="Select COUNT(*) From DanhGia dg where dg.trang_thai = 0", nativeQuery = true)
    public List<Integer> findWaitingCount();

    @Query(value="Select * From DanhGia dg where dg.trang_thai = 0", nativeQuery = true)
    public Page<DanhGia> findWaitingComment(Pageable page);

    @Query(value="Select * from DanhGia dg where dg.id_dt like ?1 and dg.trang_thai = 1", nativeQuery = true)
    public List<DanhGia> findAllDanhGiaWithDienThoai(String id);
    @Modifying @Transactional
    @Query(value="Delete from DanhGia where stt = ?1", nativeQuery = true)
    public void deleteById(int id);
    @Modifying @Transactional
    @Query(value="Update DanhGia set trang_thai = 1 where stt = ?1", nativeQuery = true)
    public void acceptById(int id);
    @Modifying @Transactional
    @Query(value="Insert into DanhGia(sdt, noi_dung, trang_thai, id_dt) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    public void addDanhGia(String sdt, String noidung, int tinhtrang, String id);
}
