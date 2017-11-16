package com.poc.repository;

import com.poc.entity.construction.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ITaskRepository extends ElasticsearchRepository<Task, String> {
}
