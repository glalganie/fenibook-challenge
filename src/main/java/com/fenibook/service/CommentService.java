package com.fenibook.service;

import com.fenibook.model.Comment;
import java.util.List;

public interface CommentService {

    /**
     * Recupera tutti i commenti associati a un libro specifico.
     *
     * @param bookId L'ID del libro.
     * @return Una lista di commenti, ordinata per data di creazione decrescente.
     */
    List<Comment> findCommentsByBookId(Long bookId);

    /**
     * Salva un nuovo commento nel database.
     *
     * @param content Il testo del commento.
     * @param bookId L'ID del libro a cui il commento è associato.
     * @param userId L'ID dell'utente che ha scritto il commento.
     * @return Il commento salvato.
     */
    Comment saveComment(String content, Long bookId, Long userId);

    /**
     * Elimina un commento. In una versione futura, si aggiungerebbe un controllo
     * per verificare che solo l'autore del commento o un admin possano eliminarlo.
     *
     * @param commentId L'ID del commento da eliminare.
     */
    void deleteComment(Long commentId);

}