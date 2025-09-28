package com.fenibook.service.impl;

import com.fenibook.dto.UserRegistrationDto;
import com.fenibook.model.User;
import com.fenibook.repository.UserRepository;
import com.fenibook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegistrationDto userDto) { // Accetta il DTO
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Errore: Username già in uso!");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Errore: Email già in uso!");
        }
        // Converte il DTO in un'entità User
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(User.Role.USER);
        return userRepository.save(user);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}