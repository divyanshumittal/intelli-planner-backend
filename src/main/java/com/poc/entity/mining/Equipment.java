package com.poc.entity.mining;

import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "equipments", type = "equipment", shards = 1, replicas = 0)
public class Equipment {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String group;

    private String createdDate;
    private String type;
    private String mode;
    private String latitude;
    private String longitude;
    private double availability;
    private double utilisation;
    private double spillage;
    private int healthScore;
    private Map<String, Object> details;

}