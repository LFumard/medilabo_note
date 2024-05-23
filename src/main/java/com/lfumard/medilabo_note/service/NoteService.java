package com.lfumard.medilabo_note.service;

import com.lfumard.medilabo_note.model.Note;
import com.lfumard.medilabo_note.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public void deleteById(String id) {
        noteRepository.deleteById(id);
    }

    public void add(Note note) {
        note.setDate(LocalDateTime.now());
        noteRepository.save(note);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }

    public Note findById(String id) {
        Optional<Note> note = noteRepository.findById(id);
        return note.orElse(new Note());
    }

    public List<Note> findAllNoteByPatientId(long patientId) {
        return noteRepository.findAllByPatientId(patientId);
    }

    public void deleteAllNoteByPatient(long patientId) {

        List<Note> noteList = noteRepository.findAllByPatientId(patientId);
        for(int i = 0; i < noteList.size(); i++) {
            noteRepository.deleteById(noteList.get(i).getId());
        }
    }
}