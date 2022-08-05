package com.kalina95.wtrs.task;

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
public class TaskFilterService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Task> filter(TaskFilterParameter taskFilterParameter) {

        Map<String, String> parameters = taskFilterParameter.parametersToMap();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(Task.class);

        List<Predicate> predicates = parameters.keySet()
                .stream()
                .filter(key -> !Strings.isNullOrEmpty((parameters.get(key))))
                .map(key -> criteriaBuilder.equal(taskRoot.get(key), parameters.get(key)))
                .collect(Collectors.toList());

        criteriaQuery.select(taskRoot).where(criteriaBuilder.and(predicates.toArray(Predicate[]::new)));

        TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
