package com.poc.service;

import java.util.List;
import java.util.Map;
import com.poc.entity.GanttTask;
import org.springframework.data.domain.Page;

public interface IGanttTaskService {

    long count();

    GanttTask create(GanttTask resource);

    GanttTask create(String id, GanttTask resource);

    Page<GanttTask> findAll();

    void delete(String id);

    Page<GanttTask> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<GanttTask> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    List<GanttTask> update(String id, GanttTask resource);

    GanttTask findOne(String id);
}
