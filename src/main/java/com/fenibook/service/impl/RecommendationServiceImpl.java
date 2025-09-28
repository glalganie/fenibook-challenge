package com.fenibook.service.impl;

import com.fenibook.model.Book;
import com.fenibook.repository.BookRepository;
import com.fenibook.service.RecommendationService; // Importa l'INTERFACCIA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService { // IMPLEMENTA l'interfaccia

    @Autowired
    private BookRepository bookRepository;

    private static final Map<String, List<String>> moodToTagMap = Map.of(
        "escape", List.of("fantasy", "sci-fi", "adventure", "dystopian"),
        "reflect", List.of("inspirational", "thought-provoking", "biography", "philosophy"),
        "laugh", List.of("humor", "comedy", "satire"),
        "thrill", List.of("mystery", "thriller", "suspense", "horror", "fast-paced")
    );

    @Override // Annotazione per indicare che stiamo implementando un metodo dell'interfaccia
    public Optional<Book> getRecommendation(String mood, Long userId) {
        // Ottiene la lista di tag associati all'umore
        List<String> targetTags = moodToTagMap.get(mood.toLowerCase());

        if (targetTags == null || targetTags.isEmpty()) {
            return Optional.empty(); // Nessun tag valido per questo umore
        }

        // Cerca nella libreria "Da Leggere" dell'utente i libri che corrispondono ai tag.
        List<Book> candidateBooks = bookRepository.findToReadBooksByUserAndTags(
            userId, 
            targetTags, 
            PageRequest.of(0, 10)
        );

        if (candidateBooks.isEmpty()) {
            return Optional.empty(); // Nessun libro trovato che corrisponda
        }
        
        // Mescola la lista e prendi il primo risultato.
        Collections.shuffle(candidateBooks);
        
        return Optional.of(candidateBooks.get(0));
    }
}