package com.blog.blog.Controller;

import com.blog.blog.model.BaiViet;
import com.blog.blog.model.DanhGia;
import com.blog.blog.services.DanhGiaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DanhGiaController {

    @Autowired
    DanhGiaService danhGiaService;

    @PostMapping("/comment")
    public String submitEvaluate(@ModelAttribute("danhGia")DanhGia danhGia, HttpSession session){
        BaiViet baiViet = (BaiViet) session.getAttribute("currentPost");
        danhGia.setIdBaiViet(baiViet);
        danhGiaService.submitEvaluate(danhGia);
        return "redirect:/post/" + baiViet.getId();
    }
}
