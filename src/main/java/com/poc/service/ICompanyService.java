package com.poc.service;

import java.util.Map;
import com.poc.entity.finance.Company;
import org.springframework.data.domain.Page;

public interface ICompanyService {

    long count();

    Company create(Company resource);

    Page<Company> findAll();

    void delete(String id);

    Page<Company> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Company> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Company resource);

    Company findOne(String id);
}
