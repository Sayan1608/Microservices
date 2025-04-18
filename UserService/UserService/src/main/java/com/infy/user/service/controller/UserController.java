package com.infy.user.service.controller;

import com.infy.user.service.dto.UserDTO;
import com.infy.user.service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
        private final UserService userService;

    public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping
        public ResponseEntity<UserDTO> saveNewUser(@RequestBody UserDTO userDTO){
            UserDTO savedUser = userService.createNewUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }

        @GetMapping(path = "/{userId}")
        public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "userId") String userId){
            UserDTO userById = userService.getUserById(userId);
            return ResponseEntity.ok(userById);
        }

        @GetMapping(path = "/userByName")
        public ResponseEntity<UserDTO> getUserByName(@RequestParam String userName){
            UserDTO userByName = userService.getUserByName(userName);
            return ResponseEntity.ok(userByName);
        }

        @GetMapping
        public ResponseEntity<List<UserDTO>> getAllUsers(){
            List<UserDTO> allUsers = userService.getAllUsers();
            return ResponseEntity.ok(allUsers);
        }

        @PutMapping(path = "/{userId}")
        public ResponseEntity<UserDTO> updateUserDetails(@PathVariable(name = "userId") String userId,
                                                         @RequestBody UserDTO userDTO){
            UserDTO updatedUser = userService.updateUserById(userId, userDTO);
            return ResponseEntity.ok(userDTO);
        }

        @DeleteMapping(path = "/{userId}")
        public ResponseEntity<String> deleteUserById(@PathVariable String userId){
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body("User with id :  "+ userId +" deleted successfully");
        }




}
