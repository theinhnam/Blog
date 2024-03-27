package com.blog.blog.Controller;

import com.blog.blog.model.BaiViet;
import com.blog.blog.model.DanhGia;
import com.blog.blog.services.DanhGiaService;
import com.blog.blog.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    DanhGiaService danhGiaService;

    @GetMapping("/post")
    public String displayPost(Model model){
        model.addAttribute("posts", postService.getAllPost());
        return "/post/index";
    }

    @GetMapping("/post/{id}")
    public String displayDetailPost(@PathVariable("id") int id, Model model, HttpSession session){
        Optional<BaiViet> postDetail = postService.getDetailPost(id);
        if (postDetail.get() == null || !(postDetail.get().getId() > 0)) {
            return "redirect:/post";
        }

        List<DanhGia> lstDanhGia = danhGiaService.findByIdBaiViet(postDetail.get().getId());
        model.addAttribute("lstDanhGia", lstDanhGia);
        session.setAttribute("currentPost", postDetail.get());
        model.addAttribute("post", postDetail.get());
        model.addAttribute("danhGia", new DanhGia());
        return "/post/blog_detail/index";
    }
}
