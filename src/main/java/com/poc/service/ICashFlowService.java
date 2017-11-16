package com.poc.service;

import java.util.Map;
import com.poc.entity.finance.CashFlow;
import org.springframework.data.domain.Page;

public interface ICashFlowService {

    long count();

    CashFlow create(CashFlow resource);

    Page<CashFlow> findAll();

    void delete(String id);

    Page<CashFlow> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<CashFlow> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, CashFlow resource);

    CashFlow findOne(String id);
}
