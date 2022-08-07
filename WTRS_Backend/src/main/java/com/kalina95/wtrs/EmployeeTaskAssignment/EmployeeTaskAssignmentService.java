package com.kalina95.wtrs.EmployeeTaskAssignment;

import com.kalina95.wtrs.employee.Employee;
import com.kalina95.wtrs.task.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeTaskAssignmentService {

    private final EmployeeTaskAssignmentRepository repository;

    public List<EmployeeTaskAssignment> getAll() {
        return repository.findAll();
    }

    public int assignTaskToEmployee(Task task, Employee employee) {
        EmployeeTaskAssignment assignment = new EmployeeTaskAssignment();
        assignment.setTask(task);
        assignment.setEmployee(employee);
        assignment.setHoursReported(0);
        repository.save(assignment);
        return assignment.getAssignmentId();
    }

    public int changeTask(int assignmentId, Task newTask) {
        EmployeeTaskAssignment assignment = repository.findById(assignmentId).orElseThrow(RuntimeException::new);
        assignment.setTask(newTask);
        repository.save(assignment);
        return assignment.getAssignmentId();
    }

    public int changeEmployee(int assignmentId, Employee newEmployee) {
        EmployeeTaskAssignment assignment = repository.findById(assignmentId).orElseThrow(RuntimeException::new);
        assignment.setEmployee(newEmployee);
        repository.save(assignment);
        return assignment.getAssignmentId();
    }

    public void deleteAssignmentById(int id) {
        repository.deleteById(id);
    }

}
