package com.blog.blog.services.Admin;

import com.blog.blog.Dto.ChuDe.StoreRequest;
import com.blog.blog.model.BaiViet;
import com.blog.blog.model.ChuDe;
import com.blog.blog.repositories.BaiVietRepository;
import com.blog.blog.repositories.ChuDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuDeService {
    @Autowired
    ChuDeRepository chuDeRepository;

    public Page<ChuDe> findAllExceptDeleted (Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,5);
        Page<ChuDe> pageData = chuDeRepository.findAllByTrangThaiOrTrangThai(BaiVietRepository.ACTIVE,BaiVietRepository.DISABLED,pageable);

        return pageData;
    }

    public boolean save (StoreRequest request) {
        ChuDe chuDe = new ChuDe();
        chuDe.setTen(request.getTen());
        chuDe.setTrangThai(request.getTrangThai());
        chuDe.setMoTa(request.getMoTa());
        try {
            chuDeRepository.save(chuDe);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update (StoreRequest newValue, ChuDe oldValue) {
        oldValue.setTrangThai(newValue.getTrangThai());
        oldValue.setTen(newValue.getTen());
        oldValue.setMoTa(newValue.getMoTa());
        try {
            chuDeRepository.save(oldValue);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete (ChuDe chuDe) {
        try {
            chuDe.setTrangThai(chuDeRepository.DELETED);
            chuDeRepository.save(chuDe);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
