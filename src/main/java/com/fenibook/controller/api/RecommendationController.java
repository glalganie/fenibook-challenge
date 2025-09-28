package com.fenibook.controller.api;

import com.fenibook.model.Book;
import com.fenibook.model.User;
import com.fenibook.service.RecommendationService;
import com.fenibook.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    @Autowired private RecommendationService recommendationService;
    @Autowired private UserService userService;
    
    @Autowired
    public RecommendationController(RecommendationService recommendationService, UserService userService) {
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    @GetMapping("/{mood}")
    public ResponseEntity<Book> getMoodRecommendation(@PathVariable String mood, Principal principal) {
        // Recupera l'utente corrente tramite il Principal fornito da Spring Security
        User currentUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + principal.getName()));
        
        // Chiama il servizio per ottenere il suggerimento
        Optional<Book> recommendation = recommendationService.getRecommendation(mood, currentUser.getId());
        
        // Restituisce il libro con status 200 OK se trovato, altrimenti 404 Not Found
        return recommendation.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }
}