package com.rosetim.userservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato do e-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(message = "A senha deve conter no mínimo 8 caracteres")
    private String password;
}
