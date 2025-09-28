package com.fenibook.controller.web;

import com.fenibook.dto.UserRegistrationDto;
import com.fenibook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDto") UserRegistrationDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Registrazione completata! Effettua il login.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }
}