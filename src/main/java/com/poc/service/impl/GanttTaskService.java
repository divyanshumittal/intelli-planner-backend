package com.poc.service.impl;

import com.poc.entity.GanttTask;
import com.poc.repository.IGanttTaskRepository;
import com.poc.service.IGanttTaskService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class GanttTaskService implements IGanttTaskService{

    private static final List<String> DEFAULT_PARAMS_TO_REMOVE = Arrays.asList("page", "size", "sortBy", "sortOrder", "fields");

    @Autowired
    IGanttTaskRepository repository;

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
    public GanttTask create(GanttTask resource) {
        String id = UUID.randomUUID().toString();
        resource.setCreatedDate(Instant.now().toString());
        resource.setId(id);
        GanttTask planning = repository.save(resource);
        return planning;
    }

    @Override
    public GanttTask create(String id, GanttTask newTask) {
        GanttTask previousTask = findOne(id);
        //GanttTask nextTask = findOne(previousTask.getToDependency());
        newTask.setId(UUID.randomUUID().toString());
        newTask.setCreatedDate(Instant.now().toString());
        
       /* previousTask.setToDependency(newTask.getId());
        newTask.setFromDependency(previousTask.getId());
        if(nextTask != null) {
            newTask.setToDependency(nextTask.getId());
            nextTask.setFromDependency(newTask.getId());
        }
        GanttTask createdNewTask = repository.save(newTask);
        repository.save(previousTask);

        if(nextTask != null) {
            repository.save(nextTask);
            int daysGap = getDaysGap(nextTask.getStartDate(), nextTask.getEndDate());
            nextTask.setStartDate(newTask.getEndDate());
            nextTask.setEndDate(addDaysToDate(daysGap, nextTask.getStartDate()));
            update(nextTask.getId(), nextTask);
        }*/

       GanttTask createdNewTask = repository.save(newTask);
        return createdNewTask;
    }

    @Override
    public Page<GanttTask> findAll() {
        return repository.findAll(constructPageRequest(0, 50, "createdDate", "ASC"));
    }

    @Override
    public void delete(String id) {
        GanttTask ganttTask = findOne(id);
        GanttTask fromGanttTask = findOne(ganttTask.getFromDependency());
        fromGanttTask.setToDependency(ganttTask.getToDependency());
        repository.save(fromGanttTask);
        if(ganttTask.getToDependency() != null) {
            GanttTask toGanttTask = findOne(ganttTask.getToDependency());
            toGanttTask.setFromDependency(ganttTask.getFromDependency());
            int daysGap = getDaysGap(toGanttTask.getStartDate(), toGanttTask.getEndDate());
            toGanttTask.setStartDate(fromGanttTask.getEndDate());
            toGanttTask.setEndDate(addDaysToDate(daysGap, fromGanttTask.getEndDate()));
            update(toGanttTask.getId(), toGanttTask);
        }

        repository.delete(id);
    }

    @Override
    public Page<GanttTask> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
        return repository.findAll(constructPageRequest(page, size, sortBy, sortOrder));
    }

    @Override
    public Page<GanttTask> search(int page, int size, String sortBy, String sortOrder, Map<String, String[]> filters) {
        QueryBuilder query = addFilters(filters);
        return repository.search(query, constructPageRequest(page, size, sortBy, sortOrder));
    }

    @Override
    public List<GanttTask> update(String id, GanttTask resource) {
        GanttTask ganttTask = findOne(id);
        resource.setCreatedDate(ganttTask.getCreatedDate());
        while (org.apache.commons.lang.StringUtils.isNotBlank(ganttTask.getToDependency())) {
            GanttTask toganttTask = findOne(ganttTask.getToDependency());
            int daysGap = getDaysGap(toganttTask.getStartDate(), toganttTask.getEndDate());
            toganttTask.setStartDate(ganttTask.getEndDate());
            toganttTask.setEndDate(addDaysToDate(daysGap, ganttTask.getEndDate()));
            repository.save(toganttTask);
            ganttTask = toganttTask;
        }
        repository.save(resource);
        return findAll().getContent();
    }

    @Override
    public GanttTask findOne(String id) {
        if(id == null) {
            return null;
        }
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
    
    public int getDaysGap(String date1, String date2) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        int daysGap = 0;
        try {
            Date date11 = format.parse(date1);
            Date date22 = format.parse(date2);
            daysGap = Days.daysBetween(new DateTime(date11), new DateTime(date22)).getDays();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return daysGap;
    }
    
    public String addDaysToDate(int days, String date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String output = "";
        try {
            Date date1 = format.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DATE, days); // Adding 5 days
            output = format.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return output;
    }
}
