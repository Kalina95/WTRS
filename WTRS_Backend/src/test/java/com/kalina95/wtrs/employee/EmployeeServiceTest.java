package com.kalina95.wtrs.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {


    List<Employee> listOfEmployees;
    Employee employee1;
    Employee employee2;
    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;


    @BeforeEach
    public void setup() {
        employee1 = Employee.builder()
                .employeeId(1)
                .pesel("95052501234")
                .firstName("Grzegorz")
                .lastName("Brzęczyszczykiewicz")
                .secondName("Marian")
                .companyRole(CompanyRole.DIRECTOR)
                .user(null)
                .assignment(null)
                .grossSalary(8000.00)
                .manager(null)
                .build();
        employee2 = Employee.builder()
                .employeeId(2)
                .pesel("95052502345")
                .firstName("Robert")
                .lastName("Kowalski")
                .secondName("Karol")
                .companyRole(CompanyRole.MANAGER)
                .user(null)
                .assignment(null)
                .grossSalary(5000.00)
                .manager(employee1)
                .build();
        listOfEmployees = newArrayList(employee1, employee2);
    }

    @Test
    void getAll_returnsListOfDto() {
        //given
        when(repository.findAll()).thenReturn(listOfEmployees);
        //when/then
        assertThat(service.getAll().get(0).getFirstName()).isEqualTo("Grzegorz");
        assertThat(service.getAll().get(0)).isInstanceOf(EmployeeDto.class);
    }

    @Test
    void getById_returnsOneDto() {
        //given
        int index = 0;
        when(repository.findById(index)).thenReturn(Optional.ofNullable(listOfEmployees.get(index)));
        //when/then
        assertThat(service.getById(0).getFirstName()).isEqualTo("Grzegorz");
        assertThat(service.getById(0)).isInstanceOf(EmployeeDto.class);
    }

    @Test
    void create_returnsEmployeeId() {
        //given
        Employee employeeToCreate = Employee.builder()
                .employeeId(3)
                .pesel("95052502345")
                .firstName("Robert")
                .lastName("Kowalski")
                .secondName("Karol")
                .companyRole(CompanyRole.MANAGER)
                .user(null)
                .assignment(null)
                .grossSalary(5000.00)
                .manager(employee1)
                .build();
        when(repository.save(employeeToCreate)).thenReturn(employeeToCreate);
        assertThat(service.create(employeeToCreate)).isEqualTo(0);
        verify(repository).save(employeeToCreate);
    }

    @Test
    void update_employeeExists_returnsId() {
        //given
        Employee employeeToCreate = Employee.builder()
                .employeeId(3)
                .pesel("95052502345")
                .firstName("Robert")
                .lastName("Kowalski")
                .secondName("Karol")
                .companyRole(CompanyRole.MANAGER)
                .user(null)
                .assignment(null)
                .grossSalary(5000.00)
                .manager(employee1)
                .build();
        //when/then
        when(repository.findById(1)).thenReturn(Optional.ofNullable(listOfEmployees.get(0)));
        when(repository.save(employeeToCreate)).thenReturn(employeeToCreate);
        assertThat(service.update(1,employeeToCreate)).isEqualTo(1);
        verify(repository).save(employeeToCreate);
    }

    @Test
    void update_employeeDoesNotExist_returnsId() {
        //given
        Employee employeeToCreate = Employee.builder()
                .employeeId(3)
                .pesel("95052502345")
                .firstName("Robert")
                .lastName("Kowalski")
                .secondName("Karol")
                .companyRole(CompanyRole.MANAGER)
                .user(null)
                .assignment(null)
                .grossSalary(5000.00)
                .manager(employee1)
                .build();
        //when/then
        when(repository.findById(1)).thenReturn(Optional.empty());
        when(repository.save(employeeToCreate)).thenReturn(employeeToCreate);
        assertThat(service.update(1,employeeToCreate)).isEqualTo(0);
        verify(repository).save(employeeToCreate);
    }

    @Test
    void delete_EmployeeWithThisIdExists() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(listOfEmployees.get(0)));
        service.delete(1);
        verify(repository).delete(listOfEmployees.get(0));
    }

    @Test
    void delete_EmployeeWithThisIdDoesNotExist() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(()->service.delete(1)).isInstanceOf(RuntimeException.class);
    }
}