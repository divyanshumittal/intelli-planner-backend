package com.poc.entity.finance;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "expenses", type = "expense", shards = 1, replicas = 0)
public class Expenses {

    String id;

    String name;

    String expense;

    String startDate;

    String endDate;


}
