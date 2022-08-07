package com.kalina95.wtrs.user;


import com.kalina95.wtrs.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserFilterController {

    private final UserFilterService service;

    @GetMapping("/filter")
    public ResponseEntity<List<User>> getAll(
            @RequestParam(required = false, name = "login") String login,
            @RequestParam(required = false, name = "role") String role
    ){

        UserFilterParameter parameters = UserFilterParameter.builder()
                .login(login)
                .role(new Utils().checkSystemRole(role))
                .build();


        return new ResponseEntity<>(service.filter(parameters), HttpStatus.OK);
    }
}
