package com.blog.blog.services;

import com.blog.blog.model.BaiViet;
import com.blog.blog.model.ChuDe;
import com.blog.blog.model.DanhGia;
import com.blog.blog.repositories.BaiVietRepository;
import com.blog.blog.repositories.ChuDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    ChuDeRepository chuDeRepository;

    @Autowired
    BaiVietRepository baiVietRepository;

    public List<ChuDe> findAllByTrangThai (int trangThai) {
        return chuDeRepository.findAllByTrangThai(trangThai);
    }

    public BaiViet findARandomBaiViet () {
        return baiVietRepository.findARanDomBaiViet();
    }

    public List<BaiViet> find3BaiVietMoiNhat () {
        return baiVietRepository.findTop3ByOrderByNgayDangDesc();
    }

    public BaiViet findById (int id) {
        return baiVietRepository.findById(id).orElse(null);
    }

    public List<BaiViet> findTop2BaiVietNhieuSoLuongDanhGia () {
        return baiVietRepository.findTop2BaiVietNhieuSoLuongDanhGia();
    }
}
