package com.rosetim.product_catalog_service.service.product;

import com.rosetim.product_catalog_service.domain.dto.ProductRequest;
import com.rosetim.product_catalog_service.domain.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);
    List<ProductResponse> findAll();
}
