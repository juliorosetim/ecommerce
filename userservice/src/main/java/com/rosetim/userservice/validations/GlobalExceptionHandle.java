package com.rosetim.userservice.validations;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Itera sobre todos os erros de campo encontrados na exceção
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Pega o nome do campo que falhou
            String fieldName = ((FieldError) error).getField();
            // Pega a mensagem de erro (a que você definiu na sua anotação)
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleCustomValidationExceptions(ConstraintViolationException ex) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("errors",
                    ex.getConstraintViolations()
                            .stream()
                            .map(error ->
                                    Map.of("campo", error.getPropertyPath().toString(), "mensagem", error.getMessage())
                                )
                            .collect(Collectors.toList())
                  );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();

        String message = "";
        if (ex.getCause() != null && ex.getCause().getMessage().contains("uc_tb_users_email")) {
            message = "E-mail já cadastrado no sistema.";
        }

        error.put("error", message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
