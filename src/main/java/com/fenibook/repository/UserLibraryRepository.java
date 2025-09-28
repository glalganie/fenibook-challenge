package com.fenibook.repository;

import com.fenibook.model.Book;
import com.fenibook.model.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, Long> {
    @Query("SELECT ul.book FROM UserLibrary ul WHERE ul.user.id = :userId AND ul.status = 'TO_READ'")
    List<Book> findToReadBooksByUserId(Long userId);
}