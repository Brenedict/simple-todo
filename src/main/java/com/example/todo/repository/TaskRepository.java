package com.example.todo.repository;

import com.example.todo.model.Task;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByStatusFalseOrderByDateAsc();

    List<Task> findAllByStatusTrueOrderByDateAsc();

    @Transactional
    void deleteAllByStatusFalse();

    @Transactional
    void deleteAllByStatusTrue();
}

