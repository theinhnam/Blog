package com.blog.blog.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) boolean error,
            @RequestParam(value = "logout", required = false) boolean logout,
            Model model
    ){
        String errorMsg = "";


        if(error){
            errorMsg = "Vui lòng kiểm tra lại tài khoản hoặc mật khẩu";
        }

        if(logout){
            errorMsg = "Đăng xuất thành công";
        }

        model.addAttribute("errorMsg", errorMsg);
        return "/login/index.html";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login.html";
    }
}
