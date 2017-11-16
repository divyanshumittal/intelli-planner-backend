package com.poc.repository;

import com.poc.entity.mining.Crusher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ICrusherRepository extends ElasticsearchRepository<Crusher, String> {
}
