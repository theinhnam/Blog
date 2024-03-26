package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tai_khoan")
@Data
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    private String password;

    private String hoTen;

    private int trangThai;

    private String quyen;

    @OneToMany(mappedBy = "nguoiDang", fetch = FetchType.EAGER)
    private Set<BaiViet> baiViet = new HashSet<>();
}
