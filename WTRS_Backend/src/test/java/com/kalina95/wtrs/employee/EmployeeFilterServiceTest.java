package com.kalina95.wtrs.employee;

import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeFilterServiceTest {

    private EmployeeFilterParameter parameter;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private EmployeeFilterService service;

    @BeforeAll
    public void setup(){
        parameter = EmployeeFilterParameter.builder()
                .pesel(null)
                .firstName("Michał")
                .secondName(null)
                .lastName(null)
                .birthDay(null)
                .grossSalary(null)
                .companyRole(null)
                .build();
    }
    @Test
    void filterByOneParameter() {
    }
}