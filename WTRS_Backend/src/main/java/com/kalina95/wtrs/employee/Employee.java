package com.kalina95.wtrs.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kalina95.wtrs.EmployeeTaskAssignment.EmployeeTaskAssignment;
import com.kalina95.wtrs.user.User;
import lombok.*;
import org.springframework.lang.NonNull;


import javax.persistence.*;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private double grossSalary;

    private CompanyRole companyRole;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeTaskAssignment> assignment;

    @OneToOne(mappedBy = "employee")
    private User user;


}