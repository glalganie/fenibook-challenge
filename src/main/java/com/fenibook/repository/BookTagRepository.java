package com.fenibook.repository;

import com.fenibook.model.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {
    
    /**
     * Trova un tag in base al suo nome. Utile per recuperare un tag specifico
     * da assegnare a un libro.
     *
     * @param name Il nome del tag da cercare.
     * @return un Optional<BookTag>.
     */
    Optional<BookTag> findByName(String name);
}