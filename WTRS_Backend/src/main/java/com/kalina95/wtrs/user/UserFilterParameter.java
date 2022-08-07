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


    private String login;
    private String password;
    private SystemRole role;
    private Employee employee;

    public Map<String, ?> parametersToMap(){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("login", login);
        parameters.put("role", role);
        return parameters;
    }



}
