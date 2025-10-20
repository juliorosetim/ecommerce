package com.rosetim.userservice.validations;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "E-mail inválido";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
