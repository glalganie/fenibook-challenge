package com.fenibook.controller.web;

import com.fenibook.model.User;
import com.fenibook.service.BookService;
import com.fenibook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class DashboardController {

    @Autowired private UserService userService;
    @Autowired private BookService bookService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        model.addAttribute("toReadBooks", bookService.findToReadBooksByUser(currentUser.getId()));
        return "dashboard";
    }
}