package com.poc.entity.mining;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "productivemovements", type = "productivemovement", shards = 1, replicas = 0)
public class ProductiveMovement {

    @Id
    private String id;

    private String timeStamp;
    private Double hg_productive_movement;
    private Double w_productive_movement;
    private Double t_productive_movement;
    private Double rehandle;
    private Double total_movement;
}
