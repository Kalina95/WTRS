package com.kalina95.wtrs.filter;

import com.kalina95.wtrs.employee.Employee;
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

public class FilterController {
    private final FilterService service;

    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filter(){
        return new ResponseEntity<>(service.filterByParam(), HttpStatus.OK);
    }
}
