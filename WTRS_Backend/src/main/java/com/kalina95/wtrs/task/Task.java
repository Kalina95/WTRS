package com.kalina95.wtrs.task;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.kalina95.wtrs.employee.Employee;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startOfTask;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endOfTask;

    @ManyToOne
    private Employee employee;


}