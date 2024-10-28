package com.ngoquochuy.identity.mapper;

import com.ngoquochuy.identity.dto.request.UserCreationRequest;
import com.ngoquochuy.identity.dto.response.UserResponse;
import com.ngoquochuy.identity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
}
