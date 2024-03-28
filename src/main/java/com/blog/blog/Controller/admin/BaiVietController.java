package com.blog.blog.Controller.admin;

import com.blog.blog.Dto.BaiViet.StoreRequest;
import com.blog.blog.model.BaiViet;
import com.blog.blog.services.Admin.BaiVietService;
import jakarta.servlet.ServletContext;
import org.apache.catalina.Store;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/admin/bai-viet")
public class BaiVietController {
    @Autowired
    BaiVietService baiVietService;

    @Autowired
    ServletContext servletContext;

//    @GetMapping("/read-file/{id}")
//    @ResponseBody
//    public ResponseEntity<byte[]> readFile (@PathVariable("id") int id){
//        try {
//            String filePath = baiVietService.findById(id).getThumbnail();
//            System.out.println(id + " iddddd " + filePath);
//            byte[] buffer = Files.readAllBytes(new File(filePath).toPath());
//            return ResponseEntity.ok()
//                    .contentLength(buffer.length)
//                    .contentType(MediaType.parseMediaType("image/png"))
//                    .body(buffer);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
@GetMapping("/read-file/{id}")
@ResponseBody
public ResponseEntity<ByteArrayResource> readFile (@PathVariable("id") int id){
    try {
        String photo = baiVietService.findById(id).getThumbnail();
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
    public String index (Model model, @RequestParam Optional<Integer> pageNumber) {
        model.addAttribute("pageData", baiVietService.findAllExceptDeleted(pageNumber.orElse(0)));
        return "admin/bai_viet/index";
    }

    @GetMapping("/index-noi-dung/{id}")
    public String moreInfo (Model model, @PathVariable("id") BaiViet baiViet) {
        model.addAttribute("baiViet", baiViet);
        return "admin/bai_viet/index_noi_dung";
    }

    @GetMapping("/create")
    public String create (Model model) {
        model.addAttribute("request", new StoreRequest());
        model.addAttribute("lstChuDe", baiVietService.findAllChuDeActive());
        return "admin/bai_viet/add";
    }

    @PostMapping("/store")
    public String store (Model model,@Validated @ModelAttribute("request") StoreRequest request, Errors errors) {
        if (errors.hasErrors()){
            model.addAttribute("lstChuDe", baiVietService.findAllChuDeActive());
            return "admin/bai_viet/add";
        }else {
            baiVietService.save(request);
            return "redirect:/admin/bai-viet/index";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit (Model model, @PathVariable("id") BaiViet oldValue) {
        StoreRequest request = new StoreRequest();
        request.setId(oldValue.getId());
        request.setTrangThai(oldValue.getTrangThai());
        request.setTieuDe(oldValue.getTieuDe());
        request.setGioiThieu(oldValue.getGioiThieu());
        request.setNoiDung(oldValue.getNoiDung());
        model.addAttribute("lstChuDe", baiVietService.findAllChuDeActive());
        model.addAttribute("request", request);
        return "admin/bai_viet/update";
    }

    @PostMapping("/update/{id}")
    public String update (@PathVariable("id") BaiViet oldValue,@Validated @ModelAttribute("request") StoreRequest newValue, Errors errors) {
        if (errors.hasErrors()){
            return "admin/bai_viet/update";
        }
        baiVietService.update(newValue, oldValue);
        return "redirect:/admin/bai-viet/index";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") BaiViet baiViet) {
        baiVietService.delete(baiViet);
        return "redirect:/admin/bai-viet/index";
    }
}
