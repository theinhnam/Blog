package com.blog.blog.Controller.admin;

import com.blog.blog.Dto.ChuDe.StoreRequest;
import com.blog.blog.model.BaiViet;
import com.blog.blog.model.ChuDe;
import com.blog.blog.services.Admin.ChuDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/chu-de")
public class ChuDeController {
    @Autowired
    ChuDeService chuDeService;

    @GetMapping("/index")
    public String index (Model model, @RequestParam Optional<Integer> pageNumber) {
        model.addAttribute("pageData", chuDeService.findAllExceptDeleted(pageNumber.orElse(0)));
        return "admin/chu_de/index";
    }

    @GetMapping("/create")
    public String create (Model model) {
        model.addAttribute("request", new StoreRequest());
        return "admin/chu_de/add";
    }

    @PostMapping("/store")
    public String store (@Validated @ModelAttribute("request") StoreRequest  request, Errors errors) {
        if (errors.hasErrors()){
            return "admin/chu_de/add";
        }else {
            chuDeService.save(request);
            return "redirect:/admin/chu-de/index";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit (Model model, @PathVariable("id") ChuDe oldValue) {
        StoreRequest request = new StoreRequest();
        request.setId(oldValue.getId());
        request.setTrangThai(oldValue.getTrangThai());
        request.setTen(oldValue.getTen());
        request.setMoTa(oldValue.getMoTa());
        model.addAttribute("request", request);
        return "admin/chu_de/update";
    }

    @PostMapping("/update/{id}")
    public String update (@PathVariable("id") ChuDe oldValue, @Validated @ModelAttribute("request") StoreRequest newValue, Errors errors) {
        if (errors.hasErrors()){
            return "admin/chu_de/update";
        }
        chuDeService.update(newValue, oldValue);
        return "redirect:/admin/chu-de/index";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") ChuDe chuDe) {
        chuDeService.delete(chuDe);
        return "redirect:/admin/chu-de/index";
    }
}
