package com.blog.blog.repositories;

import com.blog.blog.model.ChuDe;
import com.blog.blog.model.LienHe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<LienHe, Integer> {
    public static final int ACTIVE = 1;
    public static final int DELETED = 2;
    Page<LienHe> findAllByTrangThai (int active,Pageable pageable);
}
