package com.lfumard.medilabo_note.controller;

import com.lfumard.medilabo_note.model.Note;
import com.lfumard.medilabo_note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_note")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping("/list/{patientId}")
    public List<Note> getNoteByPatientId(@PathVariable long patientId) {
        logger.info("New request GetMapping : getNoteByPatientId : " + patientId);
        return noteService.findAllNoteByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable("id") String id){
        logger.info("New request GetMapping : getNoteById : " + id);
        return noteService.findById(id);
    }

    @PostMapping("/addNote")
    public void addNote(@RequestBody Note note) {
        logger.info("New request PostMapping : addNote : " + note.toString());
        noteService.add(note);
    }

    @DeleteMapping("delete/{id}")
    public void deleteNoteById(@PathVariable String id) {
        logger.info("New request DeleteMapping : deleteNoteById : " + id);
        noteService.deleteById(id);
    }

    @PutMapping("/update")
    public void updateNoteById(@RequestBody Note note) {
        logger.info("New request PutMapping : updateNoteById : " + note.toString());
        noteService.update(note);
    }

    @DeleteMapping("/deleteNoteByPatientId/{patId}")
    public void deleteAllNoteByPatient(@PathVariable Long patId) {
        logger.info("New request DeleteMapping : deleteAllNoteByPatient");
        noteService.deleteAllNoteByPatient(patId);
    }

}
