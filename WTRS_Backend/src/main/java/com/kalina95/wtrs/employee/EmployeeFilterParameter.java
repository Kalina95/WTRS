package com.kalina95.wtrs.employee;

import lombok.Builder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Builder
public class EmployeeFilterParameter {


    private String pesel;
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthDay;
    private Double grossSalary;
    private CompanyRole companyRole;

    public Map<String, ?> parametersToMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pesel", this.pesel);
        parameters.put("firstName", this.firstName);
        parameters.put("secondName", this.secondName);
        parameters.put("lastName", this.lastName);
        parameters.put("birthDay", this.birthDay);
        parameters.put("grossSalary", this.grossSalary);
        parameters.put("companyRole", this.companyRole);
        return parameters;
    }
}
