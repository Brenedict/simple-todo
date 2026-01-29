package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;

@SpringBootTest
class TodoApplicationTests {

	@Autowired private TaskService service;

	@Autowired
    private TaskRepository taskRepository;

	@BeforeEach
    void setUp() {
        // Clear everything first to ensure a clean state
        taskRepository.deleteAll();

        // Create one Done task
        Task doneTask = new Task();
        doneTask.setTitle("Done Task");
        doneTask.setStatus(true);
        service.saveTask(doneTask);

        // Create one Active task
        Task activeTask = new Task();
        activeTask.setTitle("Active Task");
        activeTask.setStatus(false);
        service.saveTask(activeTask);
    }

    @Test
    void savingTaskWithoutTitle_shouldThrowException() {
        Task task = new Task();
        task.setTitle(null); 
        
        // We catch the exception thrown by the DB/Hibernate constraint
        assertThrows(Exception.class, () -> {
            service.saveTask(task);
        });
    }

    @Test
    void deleteTaskById_shouldRemoveTask() {
        List<Task> tasks = service.getAllTasks();
        int idToDelete = tasks.get(0).getId();

        service.deleteTask(idToDelete);

        List<Task> remaining = service.getAllTasks();
        assertEquals(1, remaining.size());
        assertEquals("Active Task", remaining.get(0).getTitle());
    }

    @Test
    void shouldDeleteOnlyActiveTasksWhenFlagIsTrue() {
        // Your logic: onlyActive=true calls deleteAllByStatusFalse
        service.deleteAllTasks(true);

        List<Task> remaining = service.getAllTasks();
        assertEquals(1, remaining.size());
        assertEquals("Done Task", remaining.get(0).getTitle());
    }

    @Test
    void shouldDeleteOnlyDoneTasksWhenFlagIsFalse() {
        // Your logic: onlyActive=false calls deleteAllByStatusTrue
        service.deleteAllTasks(false);

        List<Task> remaining = service.getAllTasks();
        assertEquals(1, remaining.size());
        assertEquals("Active Task", remaining.get(0).getTitle());
    }
}
