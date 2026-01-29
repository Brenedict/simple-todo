package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.example.todo.model.Task;

public class TasksModelTest {
    
    @Test
    void testNoteCreation() {
        Task note = new Task();
        note.setTitle("Study JUnit");
        note.setDate("2023-01-01");
        note.setStatus(false);

        assertEquals("Study JUnit", note.getTitle());
        assertEquals("2023-01-01", note.getDate());
        assertFalse(note.getStatus());
    }
}
