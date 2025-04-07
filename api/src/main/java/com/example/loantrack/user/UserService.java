package com.example.loantrack.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        try {
            return userRepository.save(user); // Let the DB handle the insert and uniqueness check
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Phone number already exists", ex); // Handle the exception
        }
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber).orElseThrow( () -> new NoSuchElementException("No user found with phone number: " + phoneNumber));
    }
}