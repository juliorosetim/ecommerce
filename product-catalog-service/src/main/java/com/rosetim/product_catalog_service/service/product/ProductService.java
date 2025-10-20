package com.rosetim.product_catalog_service.service.product;

import com.rosetim.product_catalog_service.model.dto.ProductRequest;
import com.rosetim.product_catalog_service.model.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);
    List<ProductResponse> findAll();
}
