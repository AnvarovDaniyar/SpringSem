package org.example.repository;

import org.example.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс репозитория для работы с данными
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}

