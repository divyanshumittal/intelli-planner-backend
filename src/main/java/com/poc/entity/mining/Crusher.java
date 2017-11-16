package com.poc.entity.mining;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "crushers", type = "crusher", shards = 1, replicas = 0)
public class Crusher {

    @Id
    private String id;

    @NotNull
    private String name;

    List<CrusherData> data;

}

