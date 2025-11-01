package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.request.UserCreationRequest;
import com.swp391.bookverse.dto.request.UserUpdateRequest;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.User;
import com.swp391.bookverse.enums.Role;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.mapper.UserMapper;
import com.swp391.bookverse.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author huangdat
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    /**
     * Creates a new user in the system.
     * @param request the request object containing user details
     * @return user the created user entity
     */
    public User createUser(UserCreationRequest request) {

        // make sure the username of request object is not already taken. Stop the process if it is.
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        // Create a new User entity with encoded password
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode((request.getPassword())));


        // Set default role USER
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.name());
        user.setRoles(roles);

        // Save the user to the repository and return the saved entity
        return userRepository.save(user);
    }

    /**
     * Fetches all users from the system.
     * @return List<User> a list of all users
     */
    public List<UserResponse> getUsers() {
        // throw exception if there are no user entity store in DB
        if (userRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_USERS_STORED);
        }

        // transfer list of User to list of UserResponse
        List<User> users = userRepository.findAll();
        List<UserResponse> usersResponses = new ArrayList<>(users.size());
        for (int i = 0; i < users.size(); i++) {
            usersResponses.add(userMapper.toUserResponse(users.get(i)));
        }

        // Fetch all users from the repository
        return usersResponses;
    }

    public UserResponse signupUser(UserCreationRequest request) {

        // make sure the username of request object is not already taken. Stop the process if it is.
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        // Create a new User entity with encoded password
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode((request.getPassword())));
        // Set default role USER
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.name());
        user.setRoles(roles);
        // Save the user to the repository and return the saved entity
        return userMapper.toUserResponse(userRepository.save(user));
    }

    /**
     * Fetches a user by their ID.
     * @param id the ID of the user to fetch
     * @return User the user entity with the specified ID
     */
    // Only ADMIN and user himself can get user info
//    @PostAuthorize("hasAuthority('SCOPE_ADMIN') or returnObject.username == authentication.name")
    public UserResponse getUserById(String id) {
        // Fetch a user by ID from the repository
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    /**
     * Updates an existing user in the system.
     * @param id the ID of the user to update
     * @param request the request object containing updated user details
     * @return user the updated user entity
     */
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(request, existingUser);

        // encoding password
        existingUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));

        return userMapper.toUserResponse(userRepository.save(existingUser));
    }

    public UserResponse changeUserRole(String id) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Toggle between CUSTOMER and STAFF roles
        if (existingUser.getRoles().contains(Role.CUSTOMER.name())) {
            existingUser.getRoles().remove(Role.CUSTOMER.name());
            existingUser.getRoles().add(Role.STAFF.name());
        } else if (existingUser.getRoles().contains(Role.STAFF.name())) {
            existingUser.getRoles().remove(Role.STAFF.name());
            existingUser.getRoles().add(Role.CUSTOMER.name());
        }

        return userMapper.toUserResponse(userRepository.save(existingUser));
    }

    public List<UserResponse> getCustomers(){
        // throw exception if there are no user entity store in DB
        if (userRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_USERS_STORED);
        }
        // Fetch users with role CUSTOMER
        List<User> customers = userRepository.findByRolesContaining(Role.CUSTOMER.name());
        List<UserResponse> customerResponses = new ArrayList<>(customers.size());
        for (User customer : customers) {
            customerResponses.add(userMapper.toUserResponse(customer));
        }
        return customerResponses;
    }

    public List<UserResponse> getStaffs(){
        // throw exception if there are no user entity store in DB
        if (userRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_USERS_STORED);
        }
        //  Fetch users with role STAFF
        List<User> staffs = userRepository.findByRolesContaining(Role.STAFF.name());
        List<UserResponse> staffResponses = new ArrayList<>(staffs.size());
        for (User staff : staffs) {
            staffResponses.add(userMapper.toUserResponse(staff));
        }
        return staffResponses;
    }

    public Boolean isActiveUser(String id) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return existingUser.isActive();
    }

    public List<UserResponse> getActiveUsers() {
        // throw exception if there are no user entity store in DB
        if (userRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_USERS_STORED);
        }

        // Fetch users who are active
        List<User> activeUsers = userRepository.findAll().stream().filter(User::isActive).toList();
        List<UserResponse> activeUserResponses = new ArrayList<>(activeUsers.size());
        for (User activeUser : activeUsers) {
            activeUserResponses.add(userMapper.toUserResponse(activeUser));
        }
        return activeUserResponses;
    }

    public List<UserResponse> getInactiveUsers() {
        // throw exception if there are no user entity store in DB
        if (userRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_USERS_STORED);
        }

        // Fetch users who are inactive
        List<User> inactiveUsers = userRepository.findAll().stream().filter(user -> !user.isActive()).toList();
        List<UserResponse> inactiveUserResponses = new ArrayList<>(inactiveUsers.size());
        for (User inactiveUser : inactiveUsers) {
            inactiveUserResponses.add(userMapper.toUserResponse(inactiveUser));
        }
        return inactiveUserResponses;
    }

    public UserResponse changeActiveUserById(boolean active, String id) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        existingUser.setActive(active);

        return userMapper.toUserResponse(userRepository.save(existingUser));
    }
}
