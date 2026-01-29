package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.model.Note;
import com.example.todo.service.NotesService;

@SpringBootTest
class TodoApplicationTests {

	@Autowired private NotesService service;

    @Test
    void savingNoteWithoutTitle_shouldThrowException() {
        Note note = new Note();
        note.setTitle(null); // This should fail based on your constraints
        
        assertThrows(Exception.class, () -> {
            service.saveNote(note);
        });
    }

}
