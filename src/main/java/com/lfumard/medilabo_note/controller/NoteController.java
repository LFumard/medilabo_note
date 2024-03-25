package com.lfumard.medilabo_note.controller;

import com.lfumard.medilabo_note.model.Note;
import com.lfumard.medilabo_note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    /*public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }*/

    @GetMapping("/list")
    public List<Note> listNotes() {
        logger.info("New request GetMapping : show all Note");
        return noteService.findAll();
    }

    @GetMapping("/list/{patientId}")
    public List<Note> getNoteByPatientId(@PathVariable long patientId) {
        logger.info("New request GetMapping : getNoteByPatientId : " + patientId);
        return noteService.findAllNoteByPatientId(patientId);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
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

    @DeleteMapping("/deleteAll")
    public void deleteAllNote() {
        logger.info("New request DeleteMapping : deleteAllNote");
        noteService.deleteAll();
    }
}
