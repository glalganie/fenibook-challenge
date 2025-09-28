package com.fenibook.controller.web;

import com.fenibook.model.Book;
import com.fenibook.model.Comment;
import com.fenibook.model.User;
import com.fenibook.service.BookService;
import com.fenibook.service.CommentService;
import com.fenibook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired private BookService bookService;
    @Autowired private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @GetMapping("/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
     // Recupera i commenti associati a questo libro
        List<Comment> comments = commentService.findCommentsByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("comments", comments); // Aggiungi i commenti al model
        
        return "book-details";
    }

    @PostMapping("/add-to-library/{bookId}")
    public String addBookToLibrary(@PathVariable Long bookId, Principal principal, RedirectAttributes redirectAttributes) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        bookService.addBookToUserLibrary(currentUser.getId(), bookId);
        redirectAttributes.addFlashAttribute("successMessage", "Libro aggiunto alla tua libreria 'Da Leggere'!");
        return "redirect:/books";
    }
}