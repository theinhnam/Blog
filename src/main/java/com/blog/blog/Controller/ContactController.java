package com.blog.blog.Controller;

import com.blog.blog.model.LienHe;
import com.blog.blog.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/contact")
    public String displayContact(Model model){
        model.addAttribute("contact", new LienHe());
        return "contact";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(@ModelAttribute("contact") LienHe contact){
        contactService.saveMsg(contact);
        return "redirect:/contact";
    }
}
