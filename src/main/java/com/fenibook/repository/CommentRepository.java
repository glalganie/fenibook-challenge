package com.fenibook.repository;

import com.fenibook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Trova tutti i commenti per un dato ID di libro, ordinandoli dal più recente al più vecchio.
     * L'uso di "JOIN FETCH c.author" (Eager Fetching) è un'ottimizzazione per caricare
     * l'autore del commento insieme al commento stesso, evitando query N+1.
     *
     * @param bookId L'ID del libro per cui cercare i commenti.
     * @return una lista di commenti.
     */
    @Query("SELECT c FROM Comment c JOIN FETCH c.author WHERE c.book.id = :bookId ORDER BY c.createdAt DESC")
    List<Comment> findByBookIdOrderByCreatedAtDesc(Long bookId);

}