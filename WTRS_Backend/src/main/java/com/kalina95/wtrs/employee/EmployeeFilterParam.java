package com.kalina95.wtrs.employee;


import com.kalina95.wtrs.task.Task;
import com.kalina95.wtrs.user.User;
import lombok.Builder;

import java.util.Date;
import java.util.Set;

@Builder
public class EmployeeFilterParam {
    private int employeeId;


    private String pesel;
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthDay;
    private double grossSalary;
    private Set<Task> tasks;
    private User user;
}
