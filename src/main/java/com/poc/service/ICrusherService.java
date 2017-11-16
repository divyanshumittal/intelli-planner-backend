package com.poc.service;

import com.poc.entity.mining.Crusher;
import java.util.Map;
import org.springframework.data.domain.Page;

public interface ICrusherService {

    long count();

    Crusher create(Crusher resource);

    Page<Crusher> findAll();

    void delete(String id);

    Page<Crusher> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Crusher> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Crusher resource);

    Crusher findOne(String id);
}

