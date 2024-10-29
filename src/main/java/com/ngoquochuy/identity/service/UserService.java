package com.ngoquochuy.identity.service;

import com.ngoquochuy.identity.dto.request.UserCreationRequest;
import com.ngoquochuy.identity.dto.response.UserResponse;
import com.ngoquochuy.identity.entity.User;
import com.ngoquochuy.identity.exception.AppException;
import com.ngoquochuy.identity.exception.ErrorCode;
import com.ngoquochuy.identity.mapper.UserMapper;
import com.ngoquochuy.identity.repository.UserRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.PW_CONFIRMATION);
        }

        User user = userMapper.toUser(request);
        user.setCreatedAt(LocalDate.now());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

}
