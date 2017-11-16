package com.poc.entity.construction;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "tasks", type = "task", shards = 1, replicas = 0)
public class Task {

    String id;

    String name;

    String startDate;

    String EndDate;
}
