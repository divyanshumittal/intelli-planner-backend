package com.poc.service.impl;

import org.junit.Assert;
import org.junit.Test;

public class GanttTaskServiceTest {

    @Test
    public void getDaysGap() throws Exception {
        GanttTaskService s = new GanttTaskService();
        int dgap = s.getDaysGap("2017-08-20", "2017-08-25");
        Assert.assertEquals("dates not equal", 5, dgap);
    }

    @Test
    public void addDaysGap() throws Exception {
        GanttTaskService s = new GanttTaskService();
        String date = s.addDaysToDate(5, "2017-08-10");
        Assert.assertEquals("dates not equal", "2017-08-15", date);
    }
    

}