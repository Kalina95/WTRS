package com.kalina95.wtrs.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<EmployeeDto> getAll(){
        List<EmployeeDto> listWithDTOs = repository.findAll().stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toList());
        return listWithDTOs;
    }

    public EmployeeDto getById(int id){
        EmployeeDto employeeDTO = new EmployeeDto(repository.findById(id).orElseThrow(RuntimeException::new));
        return employeeDTO;
    }


    public int create(Employee employee){
        employee.setEmployeeId(0);
        repository.save(employee);
        return employee.getEmployeeId();
    }

    public int update(int id, Employee employee){
        employee.setEmployeeId(id);
        repository.save(employee);
        return employee.getEmployeeId();
    }

    public void delete(int id){
        repository.delete(repository.findById(id).orElseThrow(RuntimeException::new));
    }



}
