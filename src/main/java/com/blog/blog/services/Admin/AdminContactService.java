package com.blog.blog.services.Admin;

import com.blog.blog.model.ChuDe;
import com.blog.blog.model.LienHe;
import com.blog.blog.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminContactService {

    @Autowired
    ContactRepository contactRepository;

    public Page<LienHe> findAllExcetpDeleted (Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,5);
        Page<LienHe> pageData = contactRepository.findAllByTrangThai(ContactRepository.ACTIVE,pageable);
        return pageData;
    }

    public boolean delete (int id) {
        LienHe lienHe = contactRepository.findById(id).orElse(null);
        if (lienHe == null) {
            return false;
        }else {
            try {
                lienHe.setTrangThai(contactRepository.DELETED);
                contactRepository.save(lienHe);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
}
