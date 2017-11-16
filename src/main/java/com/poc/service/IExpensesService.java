package com.poc.service;

import java.util.Map;
import com.poc.entity.finance.Expenses;
import org.springframework.data.domain.Page;

public interface IExpensesService {

    long count();

    Expenses create(Expenses resource);

    Page<Expenses> findAll();

    void delete(String id);

    Page<Expenses> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Expenses> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Expenses resource);

    Expenses findOne(String id);
}

