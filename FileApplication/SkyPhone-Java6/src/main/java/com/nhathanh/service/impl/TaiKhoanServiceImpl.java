package com.nhathanh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhathanh.dao.TaiKhoanDAO;
import com.nhathanh.model.TaiKhoan;
import com.nhathanh.service.TaiKhoanService;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
	@Autowired
	TaiKhoanDAO dao;

	@Override
	public TaiKhoan findById(String username) {
		return dao.findById(username).get();
	}

}
