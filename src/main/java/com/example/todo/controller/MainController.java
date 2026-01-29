package com.example.todo.controller;

import com.example.todo.model.Note;
import com.example.todo.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private final NotesService _service;

    @Autowired
    public MainController(NotesService _service) {
        this._service = _service;
    }
    @GetMapping
    public String home(Model model) {
        List<Note> notes = _service.getAllNotesOrderedByDate();
        model.addAttribute("allNotes", notes);
        return "index";
    }

    @GetMapping("note-fill-up")
    public String getNoteForm() {
        return "note-form :: note-form";
    }

    @GetMapping("note-fill-up/{id}")
    public String editNoteForm(Model model, @PathVariable Integer id) {
        Note note = _service.getNoteById(id);
        model.addAttribute("note", note);
        return "note-form :: note-form";
    }

    @PostMapping("save")
    public String saveNote(Note note, Model model) {
        _service.saveNote(note);
        
        model.addAttribute("allNotes", _service.getAllNotes());
        
        // Return ONLY the fragment that wraps the notes list
        return "index :: notes-list-wrapper";
    }

    @DeleteMapping("delete/{id}")
    public String deleteNote(@PathVariable Integer id, Model model) {
        _service.deleteNote(id);
        model.addAttribute("allNotes", _service.getAllNotes());
        return "index :: notes-list-wrapper";
    }

    @DeleteMapping("clear-notes")
    public String deleteNote(Model model) {
        _service.deleteAllNotes();
        model.addAttribute("allNotes", _service.getAllNotes());
        return "index :: notes-list-wrapper";
    }

    @GetMapping("clear-modal")
    @ResponseBody
    public String clear() {
        return ""; // Returns an empty string
    }
}
