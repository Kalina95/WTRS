package com.kalina95.wtrs.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<List<Employee>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable(name = "id") int id){
        return new ResponseEntity<Employee>(service.getById(id), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Integer> post(@RequestBody Employee employee){
        return new ResponseEntity<Integer>(service.create(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> put(@RequestBody Employee employee, @PathVariable(name = "id") int id){
        return new ResponseEntity<Integer>(service.update(id, employee), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
