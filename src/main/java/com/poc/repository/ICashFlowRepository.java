package com.poc.repository;

import com.poc.entity.construction.Planning;
import com.poc.entity.finance.CashFlow;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ICashFlowRepository extends ElasticsearchRepository<CashFlow, String> {


}
