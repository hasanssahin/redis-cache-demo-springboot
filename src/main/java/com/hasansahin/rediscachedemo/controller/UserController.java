package com.hasansahin.rediscachedemo.controller;

import com.hasansahin.rediscachedemo.dto.CreateUserDto;
import com.hasansahin.rediscachedemo.dto.UpdateUserDto;
import com.hasansahin.rediscachedemo.model.User;
import com.hasansahin.rediscachedemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(updateUserDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
