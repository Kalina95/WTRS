package com.kalina95.wtrs.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskFilterController {

        private final TaskFilterService service;

        @GetMapping("/filter/")
        public ResponseEntity<List<Task>> getAll(
                @RequestParam(required = false, name = "name") String name,
                @RequestParam(required = false, name = "taskId") int taskId,
                @RequestParam(required = false, name = "startOfTask") Date startOfTask,
                @RequestParam(required = false, name = "endOfTask") Date endOfTask
        ){

            TaskFilterParameter parameters = TaskFilterParameter.builder()
                    .name(name)
                    .taskid(taskId)
                    .startOfTask(startOfTask)
                    .endOfTask(endOfTask)
                    .build();


            return new ResponseEntity<>(service.filter(parameters), HttpStatus.OK);
        }


}
