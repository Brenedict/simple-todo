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

    public List<Note> getAllNotesOrderedByDate() {
        return _repository.findAllByOrderByDateAsc();
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

    public void deleteAllNotes() {
        _repository.deleteAll();
    }  
}
