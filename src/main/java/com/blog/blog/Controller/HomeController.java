package com.blog.blog.Controller;

import com.blog.blog.repositories.ChuDeRepository;
import com.blog.blog.services.HomeService;
import com.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;


    @GetMapping("/read-file/{id}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> readFile (@PathVariable("id") int id){
        try {
            String photo = homeService.findById(id).getThumbnail();
            Path fileName = Paths.get("uploads", photo);
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
            return ResponseEntity.ok()
                    .contentLength(buffer.length)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(byteArrayResource);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/index")
    public String home (Model model) {
        model.addAttribute("lstChuDe", homeService.findAllByTrangThai(ChuDeRepository.ACTIVE));
        model.addAttribute("randomBaiViet", homeService.findARandomBaiViet());
        model.addAttribute("lst3BaiVietMoi", homeService.find3BaiVietMoiNhat());
        model.addAttribute("lst2BaiVietNhieuDanhGia", homeService.findTop2BaiVietNhieuSoLuongDanhGia());
        return "home";
    }
}
