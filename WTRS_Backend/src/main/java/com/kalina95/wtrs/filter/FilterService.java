package com.kalina95.wtrs.filter;


import com.kalina95.wtrs.employee.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class FilterService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> filterByParam(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> from = criteriaQuery.from(Employee.class);


        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        List<Employee> items =query.getResultList();
        return items;
    }


}
