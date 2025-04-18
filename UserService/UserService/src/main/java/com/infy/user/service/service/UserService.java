package com.infy.user.service.service;

import com.infy.user.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createNewUser(UserDTO userDto);
    UserDTO getUserById(String userId);
    UserDTO getUserByName(String userName);
    List<UserDTO> getAllUsers();
    UserDTO updateUserById(String userId, UserDTO userDto);
    void deleteUser(String userId);
}
