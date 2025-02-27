package com.example.pfe.controllers;

import com.example.pfe.models.User;
import com.example.pfe.dto.UserRegistrationDto;
import com.example.pfe.services.UserService;
import com.example.pfe.exceptions.ResourceNotFoundException;
import com.example.pfe.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor injection of UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            User createdUser = userService.registerUser(registrationDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Conflict status for existing user
        }
    }

    // Get a specific user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status
        }
    }

    // Get a specific user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status
        }
    }

    // Get all active users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // Update user details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status
        }
    }

    // Deactivate a user
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        try {
            userService.deactivateUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No content status
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status
        }
    }

    // Activate a user
    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        try {
            userService.activateUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No content status
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status
        }
    }

    // Change a user's password
    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        boolean passwordChanged = userService.changePassword(id, oldPassword, newPassword);
        if (passwordChanged) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No content status
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Bad request status
        }
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable User.Role role) {
        List<User> userList = userService.getUsersByRole(role);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
