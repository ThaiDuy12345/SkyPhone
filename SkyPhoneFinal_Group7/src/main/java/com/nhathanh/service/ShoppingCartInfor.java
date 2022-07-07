package com.nhathanh.service;

import com.nhathanh.model.DienThoai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartInfor {
    private DienThoai dienThoai;
    private int soLuong;
}
