package com.kalina95.wtrs.user;

import com.kalina95.wtrs.employee.Employee;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
public class UserFilterParameter {


    private String login;
    private String password;
    private SystemRole role;
    private Employee employee;

    public Map<String, ?> parametersToMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("login", login);
        parameters.put("role", role);
        return parameters;
    }


}
