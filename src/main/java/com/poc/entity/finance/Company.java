package com.poc.entity.finance;

import java.util.List;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "company", type = "company", shards = 1, replicas = 0)
public class Company {

    String id;

    String name;

    List<Expenses> expenses;

    List<CashFlow> cashFlows;
}
