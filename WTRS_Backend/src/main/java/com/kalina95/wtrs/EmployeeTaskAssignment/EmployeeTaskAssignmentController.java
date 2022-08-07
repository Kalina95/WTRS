package com.kalina95.wtrs.EmployeeTaskAssignment;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/assignment")
@RequiredArgsConstructor
public class EmployeeTaskAssignmentController {

    private final EmployeeTaskAssignmentService service;


    @GetMapping("/")
    public ResponseEntity<List<EmployeeTaskAssignment>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
