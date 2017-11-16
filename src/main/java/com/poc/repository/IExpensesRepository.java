package com.poc.repository;

import com.poc.entity.finance.Expenses;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IExpensesRepository extends ElasticsearchRepository<Expenses, String> {
}
