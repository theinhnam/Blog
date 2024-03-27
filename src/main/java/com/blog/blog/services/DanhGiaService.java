package com.blog.blog.services;

import com.blog.blog.model.DanhGia;
import com.blog.blog.repositories.DanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhGiaService {

    @Autowired
    DanhGiaRepository danhGiaRepository;

    public boolean submitEvaluate(DanhGia danhGia){
        boolean isSubmit = false;
        if (danhGiaRepository.save(danhGia).getId() > 0) {
            isSubmit = true;
        }
        return isSubmit;
    }

    public List<DanhGia> findByIdBaiViet(int idBaiViet){
        return danhGiaRepository.findByIdBaiViet(idBaiViet);
    }
}
