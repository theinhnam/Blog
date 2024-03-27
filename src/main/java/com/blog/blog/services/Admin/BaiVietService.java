package com.blog.blog.services.Admin;

import com.blog.blog.Dto.BaiViet.StoreRequest;
import com.blog.blog.model.BaiViet;
import com.blog.blog.model.ChuDe;
import com.blog.blog.repositories.BaiVietRepository;
import com.blog.blog.repositories.ChuDeRepository;
import com.blog.blog.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BaiVietService {

    private final String folder = "D:\\BlogUploadImage\\";

    @Autowired
    BaiVietRepository repository;

    @Autowired
    ChuDeRepository chuDeRepository;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    public String saveImage (MultipartFile file) {
        String filePath = folder + file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath));
            return filePath;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Page<BaiViet> findAllExceptDeleted (Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,5);
        Page<BaiViet> pageData = repository.findAllByTrangThaiOrTrangThai(BaiVietRepository.ACTIVE,BaiVietRepository.DISABLED,pageable);

        return pageData;
    }

    public BaiViet findById (int id) {
        return repository.findById(id).orElse(null);
    }

    public List<ChuDe> findAllChuDeActive() {
        return chuDeRepository.findAllByTrangThai(ChuDeRepository.ACTIVE);
    }

    public boolean save (StoreRequest request) {
        BaiViet baiViet = new BaiViet();
        baiViet.setTieuDe(request.getTieuDe());
        baiViet.setTrangThai(request.getTrangThai());
        baiViet.setNoiDung(request.getNoiDung());
        baiViet.setGioiThieu(request.getGioiThieu());
        baiViet.setThumbnail(saveImage(request.getThumbnail()));
        baiViet.setNguoiDang(taiKhoanRepository.readByEmail("admin@gmail.com"));
        ChuDe chuDe = chuDeRepository.findById(request.getIdChuDe()).get();
        baiViet.setIdChuDe(chuDe);
        try {
            repository.save(baiViet);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update (StoreRequest newValue, BaiViet oldValue) {
        oldValue.setTrangThai(newValue.getTrangThai());
        oldValue.setTieuDe(newValue.getTieuDe());
        oldValue.setGioiThieu(newValue.getGioiThieu());
        oldValue.setNoiDung(newValue.getNoiDung());
        oldValue.setThumbnail(saveImage(newValue.getThumbnail()));
        try {
            repository.save(oldValue);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete (BaiViet baiViet) {
        try {
            baiViet.setTrangThai(repository.DELETED);
            repository.save(baiViet);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
