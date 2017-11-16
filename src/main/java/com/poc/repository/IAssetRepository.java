package com.poc.repository;

import com.poc.entity.construction.Asset;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IAssetRepository extends ElasticsearchRepository<Asset, String> {
}
