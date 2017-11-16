package com.poc.repository;

import com.poc.entity.GanttTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IGanttTaskRepository extends ElasticsearchRepository<GanttTask, String> {
}
