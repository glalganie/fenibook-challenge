package com.fenibook.service;

import com.fenibook.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void addBookToUserLibrary(Long userId, Long bookId);
    List<Book> findToReadBooksByUser(Long userId);
    List<Book> findAllBooks();
    Book saveBook(Book book);
    Optional<Book> findBookById(Long id);
    void deleteBookById(Long id);
}