package com.kalina95.wtrs.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;git
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<TaskDto> getAll(){
        List<TaskDto> listOfDTOs = repository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
        return listOfDTOs;
    }

    public TaskDto getById(int id){
        TaskDto taskDTO = new TaskDto(repository.findById(id).orElseThrow(RuntimeException::new));
        return taskDTO;
    }

    public int create(Task task){
        task.setTaskid(0);
        repository.save(task);
        return task.getTaskid();
    }

    public int update(int id, Task task){
        task.setTaskid(id);
        repository.save(task);
        return task.getTaskid();
    }

    public void delete(int id){
        repository.delete(repository.findById(id).orElseThrow(RuntimeException::new));
    }
}
