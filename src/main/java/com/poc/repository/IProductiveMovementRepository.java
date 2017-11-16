package com.poc.repository;

import com.poc.entity.mining.ProductiveMovement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IProductiveMovementRepository extends ElasticsearchRepository<ProductiveMovement, String> {
}
