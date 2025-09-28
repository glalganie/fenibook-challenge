package com.fenibook.config;

import com.fenibook.model.Book;
import com.fenibook.model.BookTag;
import com.fenibook.model.User;
import com.fenibook.repository.BookRepository;
import com.fenibook.repository.BookTagRepository;
import com.fenibook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    // Iniezione delle dipendenze (corretta)
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private BookRepository bookRepository;
    @Autowired private BookTagRepository bookTagRepository;

    /**
     * Questo metodo viene eseguito da Spring Boot all'avvio dell'applicazione.
     * È annotato con @Transactional per garantire che tutte le operazioni sul database
     * avvengano all'interno di un'unica transazione sicura.
     */
    @Override
    @Transactional 
    public void run(String... args) {
        // Il metodo run ora si limita a chiamare i metodi ausiliari in ordine.
        // Tutto il codice duplicato è stato rimosso.
        createAdminUserIfNeeded();
        createDefaultTagsIfNeeded();
        createSampleBookIfNeeded();
    }

    /**
     * Crea l'utente amministratore di default solo se non ne esiste già uno.
     */
    private void createAdminUserIfNeeded() {
        if (!userRepository.existsByRole(User.Role.ADMIN)) {
            System.out.println(">>> Creating default ADMIN user... <<<");
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@fenibook.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }
    }

    /**
     * Crea i tag emozionali di default solo se la tabella dei tag è vuota.
     */
    private void createDefaultTagsIfNeeded() {
        if (bookTagRepository.count() == 0) {
            System.out.println(">>> Creating default Book Tags... <<<");
            
            BookTag fantasy = new BookTag();
            fantasy.setName("fantasy");
            fantasy.setCategory(BookTag.TagCategory.MOOD);
            bookTagRepository.save(fantasy);
            
            BookTag inspirational = new BookTag();
            inspirational.setName("inspirational");
            inspirational.setCategory(BookTag.TagCategory.MOOD);
            bookTagRepository.save(inspirational);

            BookTag fastPaced = new BookTag();
            fastPaced.setName("fast-paced");
            fastPaced.setCategory(BookTag.TagCategory.PACE);
            bookTagRepository.save(fastPaced);
        }
    }
    
    /**
     * Crea un libro di esempio con un tag associato, solo se il catalogo è vuoto.
     * Questo è utile per avere dati pronti per i test manuali.
     */
    private void createSampleBookIfNeeded() {
        if (bookRepository.count() == 0) {
             System.out.println(">>> Creating a sample Book for testing... <<<");
             
             // Trova il tag 'fantasy' che sappiamo essere stato creato dal metodo precedente.
             Optional<BookTag> fantasyTagOpt = bookTagRepository.findByName("fantasy");
             
             if (fantasyTagOpt.isPresent()) {
                 BookTag fantasyTag = fantasyTagOpt.get();
                 
                 Book sampleBook = new Book();
                 sampleBook.setTitle("Il Signore degli Anelli");
                 sampleBook.setAuthor("J.R.R. Tolkien");
                 sampleBook.setCoverImageUrl("https://images-na.ssl-images-amazon.com/images/I/51EstVXM1UL._SX331_BO1,204,203,200_.jpg");
                 
                 // Aggiungi il tag al libro.
               //  sampleBook.getTags().add(fantasyTag);
               
                 
                 // Salva il libro. JPA gestirà automaticamente la tabella di join book_tag_assignments.
                 bookRepository.save(sampleBook);
             }
        }
    }
}