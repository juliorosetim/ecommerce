package com.rosetim.product_catalog_service.repository;

import com.rosetim.product_catalog_service.domain.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
