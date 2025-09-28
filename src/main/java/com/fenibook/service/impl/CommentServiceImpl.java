package com.fenibook.service.impl;


import com.fenibook.model.Book;
import com.fenibook.model.Comment;
import com.fenibook.model.User;
import com.fenibook.repository.BookRepository;
import com.fenibook.repository.CommentRepository;
import com.fenibook.repository.UserRepository;
import com.fenibook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Comment> findCommentsByBookId(Long bookId) {
        return commentRepository.findByBookIdOrderByCreatedAtDesc(bookId);
    }

    @Override
    public Comment saveComment(String content, Long bookId, Long userId) {
        // Recupera le entità User e Book dai repository.
        // Se non vengono trovate, lancia un'eccezione.
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        
        // Crea una nuova istanza di Comment e imposta i suoi dati
        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setAuthor(author);
        newComment.setBook(book);
        
        // Salva il commento nel database e lo restituisce
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        // Aggiungere qui la logica di sicurezza (es. solo l'autore o l'admin possono cancellare)
        commentRepository.deleteById(commentId);
    }
}