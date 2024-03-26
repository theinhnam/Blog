package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bai_viet")
@Data
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tieuDe;

    private String noiDung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chu_de")
    private ChuDe idChuDe;

    private int trangThai;

    private LocalDateTime ngayDang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nguoi_dang")
    private TaiKhoan nguoiDang;

    @OneToMany(mappedBy = "idBaiViet", fetch = FetchType.EAGER)
    List<DanhGia> danhGia;
}
