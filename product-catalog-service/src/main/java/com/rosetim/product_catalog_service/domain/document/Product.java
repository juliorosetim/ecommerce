package com.rosetim.product_catalog_service.domain.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id; // O ID no MongoDB é, por padrão, uma String hexadecimal

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;

}
