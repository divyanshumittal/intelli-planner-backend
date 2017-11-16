package com.poc.service;

import java.util.Map;
import com.poc.entity.construction.Task;
import org.springframework.data.domain.Page;

public interface ITaskService {

    long count();

    Task create(Task resource);

    Page<Task> findAll();

    void delete(String id);

    Page<Task> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Task> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Task resource);

    Task findOne(String id);
}
