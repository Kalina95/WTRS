package com.kalina95.wtrs.employee;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {

    private static String URI = "/employee";

    private MockMvc mockMvc;

    @Mock
    private EmployeeService service;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(service))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @WithMockUser(value = "someUser")
    @Test
    void getAll_returnsEmptyList_statusOK() throws Exception {
        mockMvc.perform(get(URI + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @WithMockUser(value = "someUser")
    @Test
    void getById_returnsEmptyBody_statusOK() throws Exception {
        mockMvc.perform(get(URI + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "someUser")
    @Test
    void post_returns() throws Exception {

        Employee employeeToCreate = Employee.builder()
                .employeeId(0)
                .firstName("Michał")
                .lastName("Kalinowski")
                .secondName("Karol")
                .grossSalary(7000.00)
                .pesel("95052528394")
                .build();

        when(service.create(any(Employee.class))).thenReturn(1);
        JsonMapper mapper = new JsonMapper();
        String jsonAsString = mapper.writeValueAsString(employeeToCreate);

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAsString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void put() throws Exception {

        Employee employeeToUpdate = Employee.builder()
                .employeeId(0)
                .firstName("Michał")
                .lastName("Kalinowski")
                .secondName("Karol")
                .grossSalary(7000.00)
                .pesel("95052528394")
                .build();

        JsonMapper mapper = new JsonMapper();
        String jsonAsString = mapper.writeValueAsString(employeeToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put(URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAsString))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}