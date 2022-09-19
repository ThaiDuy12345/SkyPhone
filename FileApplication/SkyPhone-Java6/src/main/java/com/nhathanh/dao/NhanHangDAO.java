package com.nhathanh.dao;

import com.nhathanh.model.NhanHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhanHangDAO extends JpaRepository<NhanHang, Integer> {
    @Query(value="Select * from NhanHang nh where nh.id = ?1", nativeQuery=true)
    List<NhanHang> getNhanHangById(int id);
}
