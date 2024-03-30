package com.blog.blog.Controller.admin;

import com.blog.blog.services.Admin.AdminContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin/contact")
public class AdminContactController {

    @Autowired
    AdminContactService adminContactService;

    @GetMapping("/index")
    public String index (Model model, @RequestParam Optional<Integer> pageNumber) {
        model.addAttribute("pageData", adminContactService.findAllExcetpDeleted(pageNumber.orElse(0)));
        return "admin/contact/index";
    }

    @GetMapping("/delete/{id}")
    public String delete (Model model, @PathVariable("id") int id) {
        adminContactService.delete(id);
        return "redirect:/admin/contact/index";
    }
}
