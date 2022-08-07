package com.kalina95.wtrs.employee;

import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeFilterService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> filter(EmployeeFilterParameter employeeFilterParameter) {

        Map<String, ?> parameters = employeeFilterParameter.parametersToMap();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        List<Predicate> listOfPredicates = parameters.keySet().stream()
                .filter( key -> parameters.get(key)!=null)
                .map( key -> criteriaBuilder.equal(employeeRoot.get(key), parameters.get(key)))
                .collect(Collectors.toList());

        criteriaQuery.select(employeeRoot).where(criteriaBuilder.and(listOfPredicates.toArray(Predicate[]::new)));

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Employee> employees = typedQuery.getResultList();
        return employees;
    }

}
