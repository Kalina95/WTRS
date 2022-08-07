package com.kalina95.wtrs.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;

@Getter
public class EmployeeDto {

    private final String pesel;
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final Date birthDay;
    private final double grossSalary;
    private String companyRole;
    private Employee manager;
    private Integer managerId;
    private String managerFirstName;
    private String managerLastName;

    public EmployeeDto(Employee employee){
        this.pesel = employee.getPesel();
        this.firstName = employee.getFirstName();
        this.secondName = employee.getSecondName();
        this.lastName = employee.getLastName();
        this.birthDay = employee.getBirthDay();
        this.grossSalary = employee.getGrossSalary();
        this.companyRole = employee.getCompanyRole().name();
        this.manager = employee.getManager();
        checkIfManagerExists();
    }

    private void checkIfManagerExists(){
        if(manager!=null){
            managerId = manager.getEmployeeId();
            managerFirstName = manager.getFirstName();
            managerLastName = manager.getLastName();
        }
    }

}
