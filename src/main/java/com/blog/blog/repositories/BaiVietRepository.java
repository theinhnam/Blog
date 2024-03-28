package com.blog.blog.repositories;

import com.blog.blog.model.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiVietRepository extends JpaRepository<BaiViet,Integer> {
    public static final int ACTIVE = 1;
    public static final int DISABLED = 0;
    public static final int DELETED = 2;
    Page<BaiViet> findAllByTrangThaiOrTrangThai (int active, int disabled, Pageable pageable);

    @Query("SELECT bv FROM BaiViet bv WHERE bv.trangThai = 1 ORDER BY RAND() LIMIT 1")
    BaiViet findARanDomBaiViet ();

    List<BaiViet> findTop3ByOrderByNgayDangDesc ();

    @Query("SELECT bv FROM BaiViet bv JOIN DanhGia dg ON dg.id = bv.id\n" +
            "GROUP BY bv.id\n" +
            "ORDER BY COUNT(dg.id) DESC\n" +
            "LIMIT 2")
    List<BaiViet> findTop2BaiVietNhieuSoLuongDanhGia ();
}
