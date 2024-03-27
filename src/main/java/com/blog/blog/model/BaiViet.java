package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bai_viet")
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tieuDe;

    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "id_chu_de")
    private ChuDe idChuDe;

    private int trangThai;

    @CreatedDate
    private LocalDateTime ngayDang;

    @ManyToOne
    @JoinColumn(name = "nguoi_dang")
    private TaiKhoan nguoiDang;

    private String gioiThieu;

    private String thumbnail;

    @OneToMany(mappedBy = "idBaiViet", fetch = FetchType.EAGER)
    List<DanhGia> danhGia;
}
