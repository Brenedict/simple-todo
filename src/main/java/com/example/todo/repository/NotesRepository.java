package com.example.todo.repository;

import com.example.todo.model.Note;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
    List<Note> findAllByStatusFalseOrderByDateAsc();

    List<Note> findAllByStatusTrueOrderByDateAsc();

    @Transactional
    void deleteAllByStatusFalse();

    @Transactional
    void deleteAllByStatusTrue();
}

