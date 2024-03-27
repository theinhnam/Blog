package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "chu_de")
@Data
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ten;

    private String moTa;

    private int trangThai;
}
