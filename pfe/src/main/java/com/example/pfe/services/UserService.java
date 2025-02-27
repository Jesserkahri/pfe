package com.example.pfe.services;

import com.example.pfe.models.User;
import com.example.pfe.dto.UserRegistrationDto;
import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDto registrationDto);

    User getUserById(Long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deactivateUser(Long id);

    void activateUser(Long id);

    boolean changePassword(Long userId, String oldPassword, String newPassword);

    List<User> getUsersByRole(User.Role role);
}