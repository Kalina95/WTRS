package com.kalina95.wtrs.employee;

import com.kalina95.wtrs.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeFilterController {

    private final EmployeeFilterService service;

    @GetMapping("/filter/")
    public ResponseEntity<List<Employee>> getAll(
            @RequestParam(required = false, name = "firstName") String firstName,
            @RequestParam(required = false, name = "secondName") String secondName,
            @RequestParam(required = false, name = "lastName") String lastName,
            @RequestParam(required = false, name = "pesel") String pesel,
            @RequestParam(required = false, name = "birthDay") String birthDay,
            @RequestParam(required = false, name = "grossSalary") Double grossSalary,
            @RequestParam(required = false, name = "companyRole") String companyRole
    ) {

        EmployeeFilterParameter employeeFilterParameter = EmployeeFilterParameter.builder()
                .pesel(pesel)
                .firstName(firstName)
                .secondName(secondName)
                .lastName(lastName)
                .birthDay(new Utils().stringToDateParser(birthDay))
                .grossSalary(grossSalary)
                .companyRole(new Utils().checkCompanyRole(companyRole))
                .build();


        return new ResponseEntity<>(service.filter(employeeFilterParameter), HttpStatus.OK);
    }
}






