package com.kalina95.wtrs.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Integer> post(@RequestBody User user){
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> put(@RequestBody User user, @PathVariable(name = "id") int id){
        return new ResponseEntity<>(service.update(id, user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

