package com.blog.blog.repositories;

import com.blog.blog.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    public TaiKhoan readByEmail(String email);
}
