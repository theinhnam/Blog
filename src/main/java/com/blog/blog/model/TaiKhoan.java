package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

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
}
