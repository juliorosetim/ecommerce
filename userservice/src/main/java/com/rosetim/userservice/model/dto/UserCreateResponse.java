package com.rosetim.userservice.model.dto;

import com.rosetim.userservice.validations.EmailValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateResponse {
    private String name;
    private String email;
}
