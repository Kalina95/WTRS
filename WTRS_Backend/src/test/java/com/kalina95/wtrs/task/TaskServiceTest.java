package com.kalina95.wtrs.task;

import com.kalina95.wtrs.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    private List<Task> listOfTasks;

    private Utils parser = new Utils();




    @BeforeEach
    public void setup(){
        Task task1 = Task.builder()
                .taskid(1)
                .name("nameOfTask1")
                .startOfTask(parser.stringToDateParser("2000-05-12"))
                .endOfTask(parser.stringToDateParser("2001-04-20"))
                .hoursForTask(200)
                .allReportedHours(0)
                .isDone(false)
                .build();
        Task task2 = Task.builder()
                .taskid(2)
                .name("nameOfTask2")
                .startOfTask(parser.stringToDateParser("2003-05-15"))
                .endOfTask(parser.stringToDateParser("2004-01-10"))
                .hoursForTask(250)
                .allReportedHours(0)
                .isDone(false)
                .build();
        listOfTasks = newArrayList(task1, task2);
    }
    @Test
    void getAll_returnsTwoDtoElementsAsList() {
        when(repository.findAll()).thenReturn(listOfTasks);
        assertThat(service.getAll().get(0)).isInstanceOf(TaskDto.class);
        assertThat(service.getAll().get(0).getName()).isEqualTo("nameTask1");
    }

    @Test
    void getAll_returnsEmptyList() {
        when(repository.findAll()).thenReturn(newArrayList());
        assertThat(service.getAll().size()).isEqualTo(0);

    }

    @Test
    void getById_returnsDtoObject() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(listOfTasks.get(0)));
        assertThat(service.getAll().size()).isEqualTo(0);
    }

    @Test
    void create() {
        Task newTask = Task.builder()
                .taskid(10)
                .name("nameOfTask3")
                .startOfTask(parser.stringToDateParser("2003-05-15"))
                .endOfTask(parser.stringToDateParser("2004-01-10"))
                .hoursForTask(250)
                .allReportedHours(0)
                .isDone(false)
                .build();
        when(repository.save(newTask)).thenReturn(newTask);
        assertThat(service.create(newTask)).isEqualTo(0);
    }

    @Test
    void update_taskWithGivenIdExists() {
        int id = 1;
        Task newTask = Task.builder()
                .taskid(10)
                .name("nameOfTask3")
                .startOfTask(parser.stringToDateParser("2003-05-15"))
                .endOfTask(parser.stringToDateParser("2004-01-10"))
                .hoursForTask(250)
                .allReportedHours(0)
                .isDone(false)
                .build();
        when(repository.findById(id)).thenReturn(Optional.ofNullable(listOfTasks.get(0)));
        when(repository.save(newTask)).thenReturn(newTask);
        assertThat(service.update(1, newTask)).isEqualTo(1);

    }

    @Test
    void update_taskWithGivenIdDoesNotExist() {
        int id = 1;
        Task newTask = Task.builder()
                .taskid(10)
                .name("nameOfTask3")
                .startOfTask(parser.stringToDateParser("2003-05-15"))
                .endOfTask(parser.stringToDateParser("2004-01-10"))
                .hoursForTask(250)
                .allReportedHours(0)
                .isDone(false)
                .build();
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(repository.save(newTask)).thenReturn(newTask);
        assertThat(service.update(1, newTask)).isEqualTo(0);

    }

    @Test
    void delete_taskWithGivenIdExists() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(listOfTasks.get(0)));
        service.delete(1);
        verify(repository).delete(listOfTasks.get(0));
    }

    @Test
    void delete_taskWithGivenIdDoesNotExist() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.delete(1)).isInstanceOf(RuntimeException.class);
    }
}