package com.poc.repository;

import com.poc.entity.construction.Asset;
import com.poc.entity.finance.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ICompanyRepository extends ElasticsearchRepository<Company, String> {
}
