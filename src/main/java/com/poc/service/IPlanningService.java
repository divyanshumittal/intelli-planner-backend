package com.poc.service;

import java.util.Map;
import com.poc.entity.construction.Planning;
import org.springframework.data.domain.Page;

public interface IPlanningService {

    long count();

    Planning create(Planning resource);

    Page<Planning> findAll();

    void delete(String id);

    Page<Planning> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Planning> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Planning resource);

    Planning findOne(String id);
}
