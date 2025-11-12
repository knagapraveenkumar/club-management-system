package com.club.clubmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {

    // Show index.html (role selection)
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Admin login page
    @GetMapping("/login/admin")
    public String adminLoginPage() {
        return "login_admin";
    }

    // User login page
    @GetMapping("/login/user")
    public String userLoginPage() {
        return "login_user";
    }

    // User registration page
    @GetMapping("/register/user")
    public String userRegistrationPage() {
        return "register_user";
    }
}
