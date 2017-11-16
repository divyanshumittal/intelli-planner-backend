package com.poc.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "gantttasks", type = "gantttask", shards = 1, replicas = 0)
public class GanttTask {

    @Id
    private String id;

    String name;

    String toDependency;

    String fromDependency;

    String startDate;

    String endDate;

    int percentage;

    String createdDate;

    String color;
}
