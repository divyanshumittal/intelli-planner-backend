package com.poc.service;

import java.util.Map;
import com.poc.entity.construction.Asset;
import org.springframework.data.domain.Page;

public interface IAssetService {

    long count();

    Asset create(Asset resource);

    Page<Asset> findAll();

    void delete(String id);

    Page<Asset> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder);

    Page<Asset> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters);

    void update(String id, Asset resource);

    Asset findOne(String id);
}
