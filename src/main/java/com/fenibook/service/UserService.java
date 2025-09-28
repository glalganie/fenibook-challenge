package com.fenibook.service;

import com.fenibook.dto.UserRegistrationDto;
import com.fenibook.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByUsername(String username);
    List<User> findAllUsers();
	User registerUser(UserRegistrationDto userDto);
}