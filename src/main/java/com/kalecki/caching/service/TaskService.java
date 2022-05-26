package com.kalecki.caching.service;

import com.kalecki.caching.entity.Task;
import com.kalecki.caching.repository.TaskRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@CacheConfig(cacheNames="tasks")
public class TaskService {

    private final CacheManager cacheManager;
    private final TaskRepository repository;
    public TaskService(CacheManager cacheManager, TaskRepository repository) {
        this.cacheManager = cacheManager;
        this.repository = repository;
    }

//    @Cacheable(cacheNames = "tasks")
    @Cacheable
    public List<Task> getTasks() {

        return repository.findAll();
    }

//    @CacheEvict(value="tasks", allEntries=true)
    @CacheEvict(allEntries=true)
    public void clearTaskCache() {

    }

    public void addTask(Task task) {
        System.out.println(Objects.requireNonNull(cacheManager.getCache("tasks")).getNativeCache());
        repository.save(task);
    }

    @CachePut(value = "tasks")
    public List<Task> updateCache() {
        return repository.findAll();
    }
}
