package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository _repository;

    @Autowired
    public TaskService(TaskRepository _repository) {
        this._repository = _repository;
    }

    public List<Task> getAllTasks() {
        return _repository.findAll();
    }

    // onlyActive = true --> status = false (not done) 
    public List<Task> getAllTasksOrderedByDate(boolean onlyActive) {
        if (onlyActive) {
            return _repository.findAllByStatusFalseOrderByDateAsc();
        }

        return _repository.findAllByStatusTrueOrderByDateAsc();
    }

    public Task getTaskById(Integer id) {
        return _repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void saveTask(Task task) {
        _repository.save(task);
    }

    public void deleteTask(Integer id) {
        _repository.deleteById(id);
    }

    public void deleteAllTasks(boolean onlyActive) {
        if (onlyActive) {
            _repository.deleteAllByStatusFalse();
        } else {
            _repository.deleteAllByStatusTrue();
        }
    }  
}
