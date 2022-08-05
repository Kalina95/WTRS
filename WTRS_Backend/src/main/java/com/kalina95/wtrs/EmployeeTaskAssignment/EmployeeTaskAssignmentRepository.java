package com.kalina95.wtrs.EmployeeTaskAssignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTaskAssignmentRepository extends JpaRepository<EmployeeTaskAssignment, Integer> {
}
