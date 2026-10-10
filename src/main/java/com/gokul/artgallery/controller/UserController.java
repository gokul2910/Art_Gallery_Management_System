package com.gokul.artgallery.controller;

import com.gokul.artgallery.config.JwtUtil;

import com.gokul.artgallery.model.User;
import com.gokul.artgallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already registered"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", saved.getId(), "email", saved.getEmail(), "role", saved.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "email", user.getEmail(),
                    "role", user.getRole()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }
}