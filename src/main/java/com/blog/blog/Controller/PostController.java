package com.blog.blog.Controller;

import com.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post")
    public String displayPost(Model model){
        model.addAttribute("posts", postService.getAllPost());
        return "/post/index";
    }
}
