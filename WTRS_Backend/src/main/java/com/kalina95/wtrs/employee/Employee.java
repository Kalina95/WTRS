package com.kalina95.wtrs.employee;


import com.kalina95.wtrs.task.Task;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Builder
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @NonNull
    private String pesel;
    @NonNull
    private String firstName;

    private String secondName;
    @NonNull
    private String lastName;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date birthDay;

    private double grossSalary;

    @Column(name = "tasks")
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Task> tasks;

}