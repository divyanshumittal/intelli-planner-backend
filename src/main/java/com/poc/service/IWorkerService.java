package com.poc.service;

import java.util.Map;
import com.poc.entity.construction.Worker;
import org.springframework.data.domain.Page;

public interface IWorkerService {

    long count();

    Worker create(Worker resource);

    Page<Worker> findAll();

    void delete(String id);

    Page<Worker> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Worker> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Worker resource);

    Worker findOne(String id);
}
