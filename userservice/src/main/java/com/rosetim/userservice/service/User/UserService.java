package com.rosetim.userservice.service.User;

import com.rosetim.userservice.model.dto.UserCreateRequest;
import com.rosetim.userservice.model.dto.UserCreateResponse;
import com.rosetim.userservice.model.dto.UsersGetAllRespnse;
import com.rosetim.userservice.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserCreateResponse registerUser(UserCreateRequest userCreateRequest);

    List<UsersGetAllRespnse> getAllUsers();
}
