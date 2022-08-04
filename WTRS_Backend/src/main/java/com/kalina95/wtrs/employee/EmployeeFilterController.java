package com.kalina95.wtrs.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;


@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeFilterController {

    private final EmployeeFilterService service;

    @GetMapping("/filter/")
    public ResponseEntity<List<Employee>> getAll(
            @RequestParam(required = false, name = "firstName") String firstName,
            @RequestParam(required = false, name = "secondName") String secondName,
            @RequestParam(required = false, name = "lastName") String lastName

    ){

        Map<String, String> mapOfParams = newHashMap();
        mapOfParams.put("firstName", firstName);
        mapOfParams.put("secondName", secondName);
        mapOfParams.put("lastName", lastName);

        return new ResponseEntity<>(service.filter(mapOfParams), HttpStatus.OK);
    }
}






