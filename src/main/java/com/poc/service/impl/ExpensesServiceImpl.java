package com.poc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.poc.entity.finance.Expenses;
import com.poc.repository.IExpensesRepository;
import com.poc.service.IExpensesService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ExpensesServiceImpl implements IExpensesService {

    private static final List<String> DEFAULT_PARAMS_TO_REMOVE = Arrays.asList("page", "size", "sortBy", "sortOrder", "fields");

    @Autowired
    IExpensesRepository repository;

    public static PageRequest constructPageRequest(final int page, final int size) {
        return new PageRequest(page, size);
    }

    public static Sort constructSort(final String sortBy, final String sortOrder) {
        return constructSort(Arrays.asList(sortBy), Arrays.asList(sortOrder), true);
    }

    public static Sort constructSort(final List<String> sortByList, final List<String> sortOrderList, final boolean ignoreCase) {
        Sort sort = null;
        List<Sort.Order> orders = new ArrayList<>();
        for (int i = 0; i < sortByList.size(); i++) {
            String sortOrder = (i > (sortOrderList.size() - 1)) ? Sort.Direction.DESC.toString() : sortOrderList.get(i);
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortOrder), sortByList.get(i));
            if (ignoreCase) {
                order = order.ignoreCase();
            }
            orders.add(order);
        }
        if (!orders.isEmpty()) {
            sort = new Sort(orders);
        }
        return sort;
    }

    public static PageRequest constructPageRequest(final int page, final int size, final String sortBy, final String sortOrder) {
        return new PageRequest(page, size, constructSort(sortBy, sortOrder));
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Expenses create(Expenses crusher) {
        String id = UUID.randomUUID().toString();
        crusher.setId(id);
        Expenses savedCrusher = repository.save(crusher);
        return savedCrusher;
    }

    @Override
    public Page<Expenses> findAll() {
        return repository.findAll(constructPageRequest(0, 1, "name", "ASC"));
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Page<Expenses> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
        return repository.findAll(constructPageRequest(page, size, sortBy, sortOrder));
    }

    @Override
    public Page<Expenses> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters) {
        QueryBuilder query = addFilters(filters);
        return repository.search(query, constructPageRequest(page, size, sortBy, sortOrder));
    }

    @Override
    public void update(String id, Expenses resource) {
        Expenses crusher = findOne(id);
        if (crusher != null) {
            repository.save(crusher);
        }
    }

    @Override
    public Expenses findOne(String id) {
        return repository.findOne(id);
    }

    private BoolQueryBuilder addFilters(Map<String, String[]> filters) {
        BoolQueryBuilder qb = new BoolQueryBuilder();
        List<QueryBuilder> queries = new ArrayList<>();

        filters.entrySet().stream().filter(entry -> !DEFAULT_PARAMS_TO_REMOVE.contains(entry.getKey())).filter(entry -> entry.getValue() != null && entry.getValue().length != 0).forEach(
                entry -> Arrays.stream(entry.getValue()).filter(value -> !StringUtils.isEmpty(value)).forEach(
                        value -> queries.add(new MatchQueryBuilder(entry.getKey().replace(".search", "").toString(), entry.getValue()))));
        for (QueryBuilder query : queries) {
            qb.must(query);
        }
        return qb;
    }
}
