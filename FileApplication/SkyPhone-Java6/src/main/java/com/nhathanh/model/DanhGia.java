package com.nhathanh.model;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DANHGIA")
@Table(name = "DANHGIA")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stt;
    private String sdt;
    private String noi_dung;
    private Boolean trang_thai;
    @ManyToOne @JoinColumn(name="id_dt") @JsonIgnore
    private DienThoai dienThoai;
}
