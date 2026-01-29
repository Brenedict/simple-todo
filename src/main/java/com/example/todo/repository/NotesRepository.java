package com.example.todo.repository;

import com.example.todo.model.Note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
    List<Note> findAllByOrderByDateAsc();
}

