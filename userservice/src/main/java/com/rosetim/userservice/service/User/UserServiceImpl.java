package com.rosetim.userservice.service.User;

import com.rosetim.userservice.model.dto.UserCreateRequest;
import com.rosetim.userservice.model.dto.UserCreateResponse;
import com.rosetim.userservice.model.dto.UsersGetAllRespnse;
import com.rosetim.userservice.model.entity.UserEntity;
import com.rosetim.userservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserCreateResponse registerUser(UserCreateRequest userCreateRequest) {
        UserEntity newUser  = new UserEntity();
        BeanUtils.copyProperties(userCreateRequest, newUser);

        newUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        UserEntity userSaved = userRepository.save(newUser);

        UserCreateResponse response = new UserCreateResponse();

        BeanUtils.copyProperties(userSaved, response);

        return response;
    }

    @Override
    public List<UsersGetAllRespnse> getAllUsers() {
        List<UserEntity> all = userRepository.findAll();

        List<UsersGetAllRespnse> userResponse = new ArrayList<>();

        all.stream().forEach( user -> {
            UsersGetAllRespnse userDto = new UsersGetAllRespnse();
            BeanUtils.copyProperties(user, userDto);
            userResponse.add(userDto);
        });

        return userResponse;
    }
}
