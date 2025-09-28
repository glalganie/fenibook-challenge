package com.fenibook.service;

import com.fenibook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca l'utente nel database.
        // Se non lo trova, lancia l'eccezione che Spring Security si aspetta.
        // Poiché la nostra classe User implementa UserDetails, possiamo restituirla direttamente.
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nessun utente trovato con username: " + username));
    }
}