package com.poc.service;

import com.poc.entity.mining.ProductiveMovement;
import java.util.Map;
import org.springframework.data.domain.Page;

public interface IProductiveMovementService {

    long count();

    ProductiveMovement create(ProductiveMovement resource);

    Page<ProductiveMovement> findAll();

    void delete(String id);

    Page<ProductiveMovement> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<ProductiveMovement> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, ProductiveMovement resource);

    ProductiveMovement findOne(String id);
}

