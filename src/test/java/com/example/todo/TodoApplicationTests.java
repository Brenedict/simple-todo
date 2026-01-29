package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private TaskRepository noteRepository;

	@BeforeEach
    void setUp() {
        // Clear everything first to ensure a clean state
        noteRepository.deleteAll();

        // Create one Done note
        Task doneNote = new Task();
        doneNote.setTitle("Done Note");
        doneNote.setStatus(true);
        noteRepository.save(doneNote);

        // Create one Active note
        Task activeNote = new Task();
        activeNote.setTitle("Active Note");
        activeNote.setStatus(false);
        noteRepository.save(activeNote);
    }

    @Test
    void savingNoteWithoutTitle_shouldThrowException() {
        Task note = new Task();
        note.setTitle(null); // This should fail based on your constraints
        
        assertThrows(Exception.class, () -> {
            service.saveNote(note);
        });
    }

}
