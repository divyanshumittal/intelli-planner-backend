package com.poc.entity.construction;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "workers", type = "worker", shards = 1, replicas = 0)
public class Worker {

    String id;

    String name;
}
