package com.rosetim.product_catalog_service.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    private String description;
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    private BigDecimal price;
    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
    private Integer stockQuantity;
}
