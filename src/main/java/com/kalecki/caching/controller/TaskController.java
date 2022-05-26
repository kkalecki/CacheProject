package com.kalecki.caching.controller;

import com.kalecki.caching.entity.Task;
import com.kalecki.caching.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService service;


    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/task")
    public void addTask(@RequestBody Task task) {
        service.addTask(task);
    }

    @GetMapping("/task")
    public List<Task> getTasks() {
        return service.getTasks();
    }

    @GetMapping("/clear")
    public void clearTaskCache() {
         service.clearTaskCache();
    }

    @GetMapping("/update")
    public List<Task> updateCache() {
        return service.updateCache();
    }


}
