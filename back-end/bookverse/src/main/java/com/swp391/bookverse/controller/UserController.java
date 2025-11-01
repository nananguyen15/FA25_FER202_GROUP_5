package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.UserCreationRequest;
import com.swp391.bookverse.dto.request.UserUpdateRequest;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.User;
import com.swp391.bookverse.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author huangdat
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @PostMapping("/create")
    public APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        APIResponse<User> response = new APIResponse<>();

        response.setResult(userService.createUser(request));

       return response;
    }

    @GetMapping
    public APIResponse<List<UserResponse>> getUsers(){
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setResult(userService.getUsers());
        return response;
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update/{userId}")
    public UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @PutMapping("/change-role/{userId}")
    public UserResponse changeUserRole(@PathVariable("userId") String userId) {
        return userService.changeUserRole(userId);
    }

    @GetMapping("/customers")
    public APIResponse<List<UserResponse>> getCustomers(){
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setResult(userService.getCustomers());
        return response;
    }

    @GetMapping("/staffs")
    public APIResponse<List<UserResponse>> getStaffs(){
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setResult(userService.getStaffs());
        return response;
    }

    @GetMapping("/active")
    public APIResponse<List<UserResponse>> getActiveUsers() {
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setResult(userService.getActiveUsers());
        return response;
    }

    @GetMapping("/inactive")
    public APIResponse<List<UserResponse>> getInactiveUsers() {
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setResult(userService.getInactiveUsers());
        return response;
    }

    @PutMapping("active/{userId}")
    public UserResponse restoreUser(@PathVariable("userId") String userId) {
        return userService.changeActiveUserById(true, userId);
    }

    @PutMapping("inactive/{userId}")
    public UserResponse deleteUser(@PathVariable("userId") String userId) {
        return userService.changeActiveUserById(false, userId);
    }

}
