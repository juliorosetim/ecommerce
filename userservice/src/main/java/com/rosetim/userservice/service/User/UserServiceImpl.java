package com.rosetim.userservice.service.User;

import com.rosetim.userservice.model.dto.UserCreateRequest;
import com.rosetim.userservice.model.entity.UserEntity;
import com.rosetim.userservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUser(UserCreateRequest userCreateRequest) {
        UserEntity newUser  = new UserEntity();
        BeanUtils.copyProperties(userCreateRequest, newUser);

        newUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        return userRepository.save(newUser);
    }
}
