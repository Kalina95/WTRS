package com.kalina95.wtrs.task;

import lombok.Builder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Builder
public class TaskFilterParameter {

    private String name;
    private Date startOfTask;
    private Date endOfTask;

    public Map<String, ?> parametersToMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", this.name);
        parameters.put("startOfTask", this.startOfTask);
        parameters.put("endOfTask", this.endOfTask);
        return parameters;
    }
}
