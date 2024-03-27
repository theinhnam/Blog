package com.blog.blog.repositories;

import com.blog.blog.model.BaiViet;
import com.blog.blog.model.ChuDe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChuDeRepository extends JpaRepository<ChuDe,Integer> {
    public static final int ACTIVE = 1;
    public static final int DISABLED = 0;
    public static final int DELETED = 2;
    Page<ChuDe> findAllByTrangThaiOrTrangThai (int active, int disabled, Pageable pageable);
    List<ChuDe> findAllByTrangThai (int trangThai);
}
