package com.fenibook.controller.web;

import com.fenibook.model.Book;
import com.fenibook.service.BookService;
import com.fenibook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired private BookService bookService;
    @Autowired private UserService userService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    // Gestione Libri
    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "admin/book-list";
    }

    @GetMapping("/books/new")
    public String showNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/book-form";
    }

    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Libro salvato con successo.");
        return "redirect:/admin/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
        return "admin/book-form";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBookById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Libro eliminato con successo.");
        return "redirect:/admin/books";
    }

    // Gestione Utenti
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin/user-list";
    }
}