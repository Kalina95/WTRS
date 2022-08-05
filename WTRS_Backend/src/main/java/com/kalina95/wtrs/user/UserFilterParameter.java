package com.kalina95.wtrs.user;

import com.kalina95.wtrs.employee.Employee;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;

@Builder
public class UserFilterParameter {

    private int userId;
    private String login;
    private String password;
    private String role;
    private Employee employee;

    public Map<String, String> parametersToMap(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", String.valueOf(userId));
        parameters.put("login", String.valueOf(login));
        parameters.put("role", String.valueOf(role));
        return parameters;
    }



}
