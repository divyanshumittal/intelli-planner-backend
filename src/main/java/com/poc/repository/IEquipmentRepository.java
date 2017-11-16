package com.poc.repository;

import com.poc.entity.mining.Equipment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IEquipmentRepository extends ElasticsearchRepository<Equipment, String> {
}
