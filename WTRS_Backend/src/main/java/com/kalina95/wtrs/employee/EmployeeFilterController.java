package com.kalina95.wtrs.employee;

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
            @RequestParam(required = false, name = "lastName") String lastName

    ) {

        EmployeeFilterParameter employeeFilterParameter = EmployeeFilterParameter.builder()
                .firstName(firstName)
                .secondName(secondName)
                .lastName(lastName)
                .build();


        return new ResponseEntity<>(service.filter(employeeFilterParameter), HttpStatus.OK);
    }
}






