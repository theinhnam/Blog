package com.blog.blog.repositories;

import com.blog.blog.model.BaiViet;
import com.blog.blog.model.ChuDe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<BaiViet, Integer> {

    public List<BaiViet> findBaiVietByIdChuDe(ChuDe idChuDe);
}
