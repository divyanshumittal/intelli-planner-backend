package com.poc.repository;

import com.poc.entity.construction.Worker;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IWorkerRepository extends ElasticsearchRepository<Worker, String> {
}
