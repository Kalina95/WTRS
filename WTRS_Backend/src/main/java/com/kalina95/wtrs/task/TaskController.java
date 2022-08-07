package com.kalina95.wtrs.task;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Integer> post(@RequestBody Task task) {
        return new ResponseEntity<>(service.create(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> put(@RequestBody Task task, @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(service.update(id, task), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
