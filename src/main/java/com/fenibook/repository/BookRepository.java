package com.fenibook.repository;

import com.fenibook.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Trova i libri nella lista "Da Leggere" di un utente che corrispondono a una lista di tag.
     * Questa è una query JPQL esplicita che dice a Spring come navigare le relazioni.
     *
     * @param userId L'ID dell'utente.
     * @param tagNames La lista di nomi di tag da cercare.
     * @param pageable Oggetto per la paginazione.
     * @return Una lista di libri.
     */
    @Query("SELECT b FROM Book b " +
           "JOIN b.tags t " +
           "WHERE t.name IN :tagNames " +
           "AND b.id IN (SELECT ul.book.id FROM UserLibrary ul WHERE ul.user.id = :userId AND ul.status = 'TO_READ')")
    List<Book> findToReadBooksByUserAndTags(@Param("userId") Long userId, 
                                            @Param("tagNames") List<String> tagNames, 
                                            Pageable pageable);
}