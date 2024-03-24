package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lien_he")
public class LienHe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tieuDe;

    private String noiDung;

    private String email;

    private int trangThai;
}
