package com.blog.blog.services;

import com.blog.blog.model.BaiViet;
import com.blog.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<BaiViet> getAllPost(){
        return postRepository.findAll();
    }

    public Optional<BaiViet> getDetailPost(int id){
        return postRepository.findById(id);
    }
}
