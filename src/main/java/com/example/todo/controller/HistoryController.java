package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history/")
public class HistoryController {
    private final TaskService _service;

    @Autowired
    public HistoryController(TaskService _service) {
        this._service = _service;
    }

    @DeleteMapping("delete/{id}")
    public String deleteTask(@PathVariable Integer id, Model model) {
        _service.deleteTask(id);
        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(false));
        return "history :: history-tasks-list-wrapper";
    }

    @DeleteMapping("clear-tasks")
    public String clearTasks(Model model) {
        _service.deleteAllTasks(false);
        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(false));
        return "history :: history-tasks-list-wrapper";
    }

    @PatchMapping("mark-not-done/{id}")
    public String markNotDone(@PathVariable Integer id, Model model) {
        Task task = _service.getTaskById(id);
        task.setStatus(false);
        _service.saveTask(task);
        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(false));
        return "history :: history-tasks-list-wrapper";
    }

}
