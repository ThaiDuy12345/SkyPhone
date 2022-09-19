package com.nhathanh.service;

import com.nhathanh.model.DienThoai;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class ShoppingCartService {
	private List<ShoppingCartInfor> list = new ArrayList<>();

	public List<ShoppingCartInfor> getList() {
		return this.list;
	}

	public List<ShoppingCartInfor> setList(List<ShoppingCartInfor> ds) {
		return this.list = ds;
	}

	public void removeAll() {
		this.list = null;
	}

	public void add(DienThoai dt) {
		try {
			boolean check = false;
			for (int i = 0; i < list.size(); i++) {
				// So sánh ID của điện thoại có trùng hay không!
				// Nếu có tăng số lượng lên 1
				if (list.get(i).getDienThoai().getId_dt().equals(dt.getId_dt())) {
					list.get(i).setSoLuong(list.get(i).getSoLuong() + 1);
					check = true;
					break;
				}
			}
			if (!check) {
				list.add(new ShoppingCartInfor(dt, 1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void remove(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDienThoai().getId_dt().equals(id)) {
				if (list.get(i).getSoLuong() < 2) {
					list.remove(i);
				} else {
					list.get(i).setSoLuong(list.get(i).getSoLuong() - 1);
				}
				break;
			}
		}
	}

	// Lấy tổng giá tiền của các sản phẩm trong giỏ hàng
	public double getAmoutInCart() {
		double tongTien = 0;
		for (int i = 0; i < list.size(); i++) {
			tongTien += list.get(i).getSoLuong() * list.get(i).getDienThoai().getGia();
		}
		return tongTien;
	}
}
