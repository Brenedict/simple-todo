package com.example.todo.service;

import com.example.todo.model.Note;
import com.example.todo.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private final NotesRepository _repository;

    @Autowired
    public NotesService(NotesRepository _repository) {
        this._repository = _repository;
    }

    public List<Note> getAllNotes() {
        return _repository.findAll();
    }

    // onlyActive = true --> status = false (not done) 
    public List<Note> getAllNotesOrderedByDate(boolean onlyActive) {
        if (onlyActive) {
            return _repository.findAllByStatusFalseOrderByDateAsc();
        }

        return _repository.findAllByStatusTrueOrderByDateAsc();
    }

    public Note getNoteById(Integer id) {
        return _repository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public void saveNote(Note note) {
        _repository.save(note);
    }

    public void deleteNote(Integer id) {
        _repository.deleteById(id);
    }

    public void deleteAllNotes(boolean onlyActive) {
        if (onlyActive) {
            _repository.deleteAllByStatusFalse();
        } else {
            _repository.deleteAllByStatusTrue();
        }
    }  
}
