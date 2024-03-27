package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "danh_gia")
@EntityListeners(AuditingEntityListener.class)
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String noiDung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bai_viet")
    private BaiViet idBaiViet;

    @Column(name = "ten_nguoi_viet")
    private String tenNguoiViet;

    @CreatedDate
    private LocalDateTime thoiGian;
}
