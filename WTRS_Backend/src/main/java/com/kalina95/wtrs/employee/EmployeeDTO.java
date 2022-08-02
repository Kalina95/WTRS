package com.kalina95.wtrs.employee;

import lombok.Getter;
import java.util.Date;

@Getter
public class EmployeeDTO {

    private final String pesel;
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final Date birthDay;
    private final double grossSalary;

    public EmployeeDTO(Employee employee){
        this.pesel = employee.getPesel();
        this.firstName = employee.getFirstName();
        this.secondName = employee.getSecondName();
        this.lastName = employee.getLastName();
        this.birthDay = employee.getBirthDay();
        this.grossSalary = employee.getGrossSalary();
    }
}
