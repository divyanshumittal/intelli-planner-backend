package com.poc.repository;

import com.poc.entity.construction.Planning;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IPlanningRepository extends ElasticsearchRepository<Planning, String> {


}
