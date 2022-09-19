package com.nhathanh.dao;

import com.nhathanh.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {
}
