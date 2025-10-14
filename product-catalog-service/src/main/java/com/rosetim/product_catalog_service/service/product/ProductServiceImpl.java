package com.rosetim.product_catalog_service.service.product;

import com.rosetim.product_catalog_service.domain.document.Product;
import com.rosetim.product_catalog_service.domain.dto.ProductRequest;
import com.rosetim.product_catalog_service.domain.dto.ProductResponse;
import com.rosetim.product_catalog_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        productRepository.save(product);

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(product, productResponse);
            return productResponse;
        }).collect(Collectors.toList());
    }
}
