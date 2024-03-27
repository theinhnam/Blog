package com.blog.blog;

import com.blog.blog.services.Admin.BaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.nio.file.Files;

@Component
@CrossOrigin("*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
//    @Autowired
//    BaiVietService baiVietService;
//
//    @GetMapping("/read-file/{id}")
//    public byte[] readFile (@PathVariable("id") int id){
//        try {
//            String filePath = baiVietService.findById(id).getThumbnail();
//            System.out.println(id + " iddddd " + filePath);
//            return Files.readAllBytes(new File(filePath).toPath());
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
}
