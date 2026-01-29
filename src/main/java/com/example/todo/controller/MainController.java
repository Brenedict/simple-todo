package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private final TaskService _service;

    @Autowired
    public MainController(TaskService _service) {
        this._service = _service;
    }

    @GetMapping
    public String home(Model model) {
        List<Task> tasks = _service.getAllTasksOrderedByDate(true);
        model.addAttribute("allTasks", tasks);
        return "index";
    }

    @GetMapping("/history")
    public String history(Model model) {
        List<Task> tasks = _service.getAllTasksOrderedByDate(false);
        model.addAttribute("allTasks", tasks);
        return "history";
    }

    @GetMapping("task-fill-up")
    public String getTaskForm() {
        return "task-form :: task-form";
    }

    @GetMapping("task-fill-up/{id}")
    public String editTaskForm(Model model, @PathVariable Integer id) {
        Task task = _service.getTaskById(id);
        model.addAttribute("task", task);
        return "task-form :: task-form";
    }

    @PostMapping("save")
    public String saveTask(Task task, Model model) {
        _service.saveTask(task);

        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(true));

        // Return ONLY the fragment that wraps the tasks list
        return "index :: tasks-list-wrapper";
    }

    @DeleteMapping("delete/{id}")
    public String deleteTask(@PathVariable Integer id, Model model) {
        _service.deleteTask(id);
        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(true));
        return "index :: tasks-list-wrapper";
    }

    @DeleteMapping("clear-tasks")
    public String cleartasks(Model model) {
        _service.deleteAllTasks(true);
        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(true));
        return "index :: tasks-list-wrapper";
    }

    @PatchMapping("mark-done/{id}")
    public String markDone(@PathVariable Integer id, Model model) {
        Task task = _service.getTaskById(id);
        task.setStatus(true);
        _service.saveTask(task);
        model.addAttribute("allTasks", _service.getAllTasksOrderedByDate(true));
        return "index :: tasks-list-wrapper";
    }

    @GetMapping("clear-modal")
    @ResponseBody
    public String clear() {
        return ""; // Returns an empty string
    }
}
