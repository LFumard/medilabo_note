package com.lfumard.medilabo_note.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.lfumard.medilabo_note.model.Note;
import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findAllByPatientId(Long patientId);
    List<Note> findAll();
}

