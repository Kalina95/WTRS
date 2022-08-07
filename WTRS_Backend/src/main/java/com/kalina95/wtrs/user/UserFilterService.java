package com.kalina95.wtrs.user;

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

@Service
public class UserFilterService {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> filter(UserFilterParameter userFilterParameter) {

        Map<String, ?> parameters = userFilterParameter.parametersToMap();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        List<Predicate> listOfPredicates = parameters.keySet()
                .stream()
                .filter(key -> parameters.get(key) != null)
                .map(key -> criteriaBuilder.equal(userRoot.get(key), parameters.get(key)))
                .toList();

        criteriaQuery.select(userRoot).where(listOfPredicates.toArray(Predicate[]::new));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
}
