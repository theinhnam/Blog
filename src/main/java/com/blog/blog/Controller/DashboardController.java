package com.blog.blog.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String displayDashboard(HttpSession session, Authentication authentication){
        session.setAttribute("inLoggedUser", authentication.getName());
        return "dashboard.html";
    }
}
