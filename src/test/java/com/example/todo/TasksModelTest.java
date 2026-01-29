package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.example.todo.model.Task;

public class TasksModelTest {
    
    @Test
    void testTaskCreation() {
        Task task = new Task();
        task.setTitle("Study JUnit");
        task.setDate("2023-01-01");
        task.setStatus(false);

        assertEquals("Study JUnit", task.getTitle());
        assertEquals("2023-01-01", task.getDate());
        assertFalse(task.getStatus());
    }
}
