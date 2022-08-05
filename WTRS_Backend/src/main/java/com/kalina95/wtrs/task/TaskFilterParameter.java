package com.kalina95.wtrs.task;

import com.kalina95.wtrs.employee.Employee;
import lombok.Builder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Builder
public class TaskFilterParameter {

    private int taskid;
    private String name;
    private Date startOfTask;
    private Date endOfTask;
    private Employee employee;

    public Map<String, String> parametersToMap(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", this.name);
        parameters.put("taskId", String.valueOf(this.taskid));
        parameters.put("startOfTask", this.startOfTask.toString());
        parameters.put("endOfTask", this.endOfTask.toString());
        return parameters;
    }
}
