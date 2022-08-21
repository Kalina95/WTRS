package com.kalina95.wtrs.user;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.kalina95.wtrs.task.Task;
import com.kalina95.wtrs.task.TaskController;
import com.kalina95.wtrs.task.TaskService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private static final String URI = "/user";

    private MockMvc mockMvc;

    @Mock
    private UserService service;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(service))
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
    void post_returnsOne() throws Exception {

        User user = User.builder()
                .userId(0)
                .login("login")
                .password("password")
                .role(SystemRole.ROLE_USER)
                .build();

        when(service.create(any(User.class))).thenReturn(1);
        JsonMapper mapper = new JsonMapper();
        String jsonAsString = mapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAsString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void put() throws Exception {


        User user = User.builder()
                .userId(0)
                .login("login")
                .password("password")
                .role(SystemRole.ROLE_USER)
                .build();

        JsonMapper mapper = new JsonMapper();
        String jsonAsString = mapper.writeValueAsString(user);

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