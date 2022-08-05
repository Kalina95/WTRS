package com.kalina95.wtrs.user;

import com.kalina95.wtrs.employee.Employee;
import lombok.Getter;

@Getter
public class UserDto {

    private String login;
    private String password;
    private Employee employee;

    public UserDto(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.employee = user.getEmployee();

    }
}
