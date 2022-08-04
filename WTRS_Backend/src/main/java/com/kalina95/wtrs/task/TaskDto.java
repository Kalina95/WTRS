package com.kalina95.wtrs.task;

import com.kalina95.wtrs.employee.Employee;
import lombok.Getter;
import java.util.Date;

@Getter
public class TaskDto {

    private final String name;
    private final Date startOfTask;
    private final Date endOfTask;
    private final Employee employee;



    public TaskDto(Task task){
        this.name = task.getName();
        this.startOfTask = task.getStartOfTask();
        this.endOfTask = task.getEndOfTask();
        this.employee = task.getEmployee();

    }
}
