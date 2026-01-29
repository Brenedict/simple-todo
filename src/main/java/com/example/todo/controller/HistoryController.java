package com.example.todo.controller;

import com.example.todo.model.Note;
import com.example.todo.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history/")
public class HistoryController {
    private final NotesService _service;

    @Autowired
    public HistoryController(NotesService _service) {
        this._service = _service;
    }

    @DeleteMapping("delete/{id}")
    public String deleteNote(@PathVariable Integer id, Model model) {
        _service.deleteNote(id);
        model.addAttribute("allNotes", _service.getAllNotesOrderedByDate(false));
        return "history :: history-notes-list-wrapper";
    }

    @DeleteMapping("clear-notes")
    public String clearNotes(Model model) {
        _service.deleteAllNotes(false);
        model.addAttribute("allNotes", _service.getAllNotesOrderedByDate(false));
        return "history :: history-notes-list-wrapper";
    }

    @PatchMapping("mark-not-done/{id}")
    public String markNotDone(@PathVariable Integer id, Model model) {
        Note note = _service.getNoteById(id);
        note.setStatus(false);
        _service.saveNote(note);
        model.addAttribute("allNotes", _service.getAllNotesOrderedByDate(false));
        return "history :: history-notes-list-wrapper";
    }

}
