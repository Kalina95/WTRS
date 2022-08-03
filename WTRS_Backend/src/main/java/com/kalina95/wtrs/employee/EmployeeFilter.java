package com.kalina95.wtrs.employee;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class EmployeeFilter {

    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> filter(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        Predicate predicate = criteriaBuilder.equal(employeeRoot.get("firstName"), "Władysław");

        criteriaQuery.select(employeeRoot).where(predicate);

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Employee> employees = typedQuery.getResultList();
        return employees;
    }

}
