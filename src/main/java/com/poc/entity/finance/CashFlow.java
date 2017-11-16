package com.poc.entity.finance;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "cashFlow", type = "cashFlow", shards = 1, replicas = 0)
public class CashFlow {

    String id;

    String name;

    int cash;

    String startDate;

    String endDate;
}
