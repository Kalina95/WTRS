package com.kalina95.wtrs.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<Employee> getAll(){
        return repository.findAll();
    }

    public Employee getById(int id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }


    public int create(Employee employee){
        employee.setEmployeeId(0);
        Employee newEmployee = employee;
        repository.save(newEmployee);
        return newEmployee.getEmployeeId();
    }

    public int update(int id, Employee employee){
        repository.findById(id);
        employee.setEmployeeId(id);
        repository.save(employee);
        return employee.getEmployeeId();
    }

    public void delete(int id){
        repository.delete(repository.findById(id).orElseThrow(RuntimeException::new));
    }



}
