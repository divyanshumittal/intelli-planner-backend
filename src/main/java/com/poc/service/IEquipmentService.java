package com.poc.service;

import com.poc.entity.mining.Equipment;
import java.util.Map;
import org.springframework.data.domain.Page;

public interface IEquipmentService {

    long count();

    Equipment create(Equipment resource);

    Page<Equipment> findAll();

    void delete(String id);

    Page<Equipment> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Equipment> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Equipment resource);

    Equipment findOne(String id);
}

