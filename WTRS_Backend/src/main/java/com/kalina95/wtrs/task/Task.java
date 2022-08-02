package com.kalina95.wtrs.task;


import com.kalina95.wtrs.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskid;

    @NonNull
    private String name;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date startDate;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;

    @ManyToOne
    private Employee employee;


}