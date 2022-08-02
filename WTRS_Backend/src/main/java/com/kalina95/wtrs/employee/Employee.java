package com.kalina95.wtrs.employee;


import com.kalina95.wtrs.task.Task;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @NonNull
    private int pesel;
    @NonNull
    private String firstName;

    private String secondName;
    @NonNull
    private String lastName;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date birthDay;

    private double grossSalary;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "employee",orphanRemoval = true)
    private Set<Task> tasks;

}