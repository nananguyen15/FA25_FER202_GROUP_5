package com.swp391.bookverse.mapper;

import com.swp391.bookverse.dto.request.UserCreationRequest;
import com.swp391.bookverse.dto.request.UserUpdateRequest;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @Author huangdat
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(target = "password", ignore = true)
//    @Mapping(source = "id", target = "id")
    UserResponse toUserResponse(User user);

    void updateUser(UserUpdateRequest request, @MappingTarget User user);
}
