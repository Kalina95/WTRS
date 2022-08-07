package com.kalina95.wtrs.EmployeeTaskAssignment;

import com.kalina95.wtrs.employee.Employee;
import com.kalina95.wtrs.task.Task;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class EmployeeTaskAssignment {

    @Id
    @GeneratedValue
    private int assignmentId;
    private int hoursReported;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "taskId")
    Task task;



}
