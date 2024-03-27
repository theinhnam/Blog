package com.blog.blog.repositories;

import com.blog.blog.model.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {

    @Query("SELECT dg from DanhGia dg where dg.idBaiViet.id = :idBaiViet")
    public List<DanhGia> findByIdBaiViet(@Param("idBaiViet") int idBaiViet);
}
