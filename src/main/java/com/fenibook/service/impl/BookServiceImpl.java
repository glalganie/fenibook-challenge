package com.fenibook.service.impl;

import com.fenibook.model.Book;
import com.fenibook.model.User;
import com.fenibook.model.UserLibrary;
import com.fenibook.repository.BookRepository;
import com.fenibook.repository.UserLibraryRepository;
import com.fenibook.repository.UserRepository;
import com.fenibook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private UserLibraryRepository userLibraryRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public void addBookToUserLibrary(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        
        UserLibrary item = new UserLibrary();
        item.setUser(user);
        item.setBook(book);
        item.setStatus(UserLibrary.Status.TO_READ);
        userLibraryRepository.save(item);
    }

    @Override
    public List<Book> findToReadBooksByUser(Long userId) {
        return userLibraryRepository.findToReadBooksByUserId(userId);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}