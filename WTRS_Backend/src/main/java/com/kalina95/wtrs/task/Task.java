package com.kalina95.wtrs.task;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kalina95.wtrs.EmployeeTaskAssignment.EmployeeTaskAssignment;
import com.kalina95.wtrs.employee.Employee;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Builder
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskid;

    @NonNull
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date startOfTask;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date endOfTask;

    private int hoursForTask;

    private int allReportedHours;

    private boolean isDone;

    @OneToMany(mappedBy = "task")
    private Set<EmployeeTaskAssignment> assignment;


}