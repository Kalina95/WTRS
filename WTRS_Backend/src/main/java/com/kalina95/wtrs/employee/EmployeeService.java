package com.kalina95.wtrs.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<EmployeeDTO> getAll(){
        List<EmployeeDTO> listWithDTOs = repository.findAll().stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
        return listWithDTOs;
    }

    public EmployeeDTO getById(int id){
        EmployeeDTO employeeDTO = new EmployeeDTO(repository.findById(id).orElseThrow(RuntimeException::new));
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
