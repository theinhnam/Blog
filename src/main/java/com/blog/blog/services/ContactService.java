package com.blog.blog.services;

import com.blog.blog.model.LienHe;
import com.blog.blog.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public boolean saveMsg(LienHe contact){
        contact.setTrangThai(1);
        boolean isSave = false;
        if (contactRepository.save(contact).getId() > 0) {
            isSave = true;
        }
        return isSave;
    }
}
