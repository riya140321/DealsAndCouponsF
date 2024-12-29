package com.AuthenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.AuthenticationService.DTO.AuthDTO;
import com.AuthenticationService.feign.AdminClient;
import com.AuthenticationService.feign.UserClient;
import com.AuthenticationService.model.User;
import com.AuthenticationService.repository.UserRepository;
import com.AuthenticationService.service.JwtService;
import com.AuthenticationService.service.MyUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private AdminClient adminClient;

    @CrossOrigin(origins = "http://localhost:5173") 
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthDTO authDTO) {

        // Set default role to "user" if it's not provided
        if (authDTO.getRole() == null || authDTO.getRole().isEmpty()) {
            authDTO.setRole("user");
        }

        // Create new User object
        User user = new User();
        user.setUsername(authDTO.getUsername());
        user.setEmail(authDTO.getEmail());
        user.setPassword(authDTO.getPassword());
        user.setRole(authDTO.getRole());

        // Encrypt the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("User with username " + user.getUsername() + " already exists", HttpStatus.CONFLICT);
        }

        // Save the new user
        userRepository.save(user);

        // Call the respective service based on role
        if (user.getRole().equalsIgnoreCase("admin")) {
            adminClient.addAdmin(authDTO);
        } else if (user.getRole().equalsIgnoreCase("user")) {
            userClient.addUser(authDTO);
        }

        // Return success message with HTTP Status 201 (Created)
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (Exception e) {
            return "Invalid username or password";
        }

        return jwtService.generateToken(user.getUsername(), user.getRole());
    }
}
