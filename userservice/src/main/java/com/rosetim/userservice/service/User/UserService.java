package com.rosetim.userservice.service.User;

import com.rosetim.userservice.model.dto.UserCreateRequest;
import com.rosetim.userservice.model.entity.UserEntity;

public interface UserService {

    UserEntity registerUser(UserCreateRequest userCreateRequest);
}
