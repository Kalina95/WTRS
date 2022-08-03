package com.kalina95.wtrs.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeFilterController {

    private final EmployeeFilter service;

    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(service.filter(), HttpStatus.OK);
    }
}






