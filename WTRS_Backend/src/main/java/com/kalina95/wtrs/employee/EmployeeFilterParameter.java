package com.kalina95.wtrs.employee;

import com.kalina95.wtrs.task.Task;
import com.kalina95.wtrs.user.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

public class EmployeeFilterParameter {

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
