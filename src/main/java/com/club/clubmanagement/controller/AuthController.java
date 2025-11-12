package com.club.clubmanagement.controller;

import com.club.clubmanagement.model.User;
import com.club.clubmanagement.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Show index page (select User/Admin)
    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    // 👉 User Login Page
    @GetMapping("/login/user")
    public String showUserLoginPage() {
        return "login_user";
    }

    // 👉 Admin Login Page
    @GetMapping("/login/admin")
    public String showAdminLoginPage() {
        return "login_admin";
    }

    // 👉 User Registration Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register_user";
    }

    // ✅ User Registration Handler
    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String password,
                                Model model) {
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already registered!");
            return "register_user";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");

        userRepository.save(user);
        model.addAttribute("success", "Registered successfully! Please log in.");
        return "redirect:/auth/login/user";
    }

    // ✅ Login Handler (User/Admin)
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            HttpSession session,                      // ✅ Add this
                            Model model) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password) && user.getRole().equalsIgnoreCase(role)) {
                session.setAttribute("loggedInUserId", user.getId()); // ✅ Set session
                if (role.equalsIgnoreCase("ADMIN")) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/user/clubs";
                }
            }
        }

        model.addAttribute("error", "Invalid credentials!");
        return role.equals("ADMIN") ? "login_admin" : "login_user";
    }

}
