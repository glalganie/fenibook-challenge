package com.fenibook.service;

import com.fenibook.model.Book;
import java.util.Optional;

/**
 * Interfaccia per il servizio di raccomandazione di libri.
 * Definisce il contratto per le funzionalità legate ai suggerimenti.
 */
public interface RecommendationService {

    /**
     * Fornisce un suggerimento di libro basato sull'umore dell'utente.
     *
     * @param mood La stringa che rappresenta l'umore (es. "escape", "reflect").
     * @param userId L'ID dell'utente per cui effettuare la ricerca.
     * @return un Optional<Book> contenente il libro suggerito, o un Optional vuoto se non trova corrispondenze.
     */
    Optional<Book> getRecommendation(String mood, Long userId);

}