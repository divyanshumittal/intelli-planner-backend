package com.poc.entity.construction;

import java.util.List;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "planning", type = "plan", shards = 1, replicas = 0)
public class Planning {

    String id;

    String name;

    String startDate;

    List<Task> tasks;

    List<Worker> workers;

    List<Asset> assets;
}
