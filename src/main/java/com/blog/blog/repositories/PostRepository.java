package com.blog.blog.repositories;

import com.blog.blog.model.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<BaiViet, Integer> {
}
