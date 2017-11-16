package com.poc.entity.construction;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "assets", type = "asset", shards = 1, replicas = 0)
public class Asset {

    String id;

    String name;
}
