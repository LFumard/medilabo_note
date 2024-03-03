package com.lfumard.medilabo_note.controller;

import com.lfumard.medilabo_note.model.Note;
import com.lfumard.medilabo_note.service.NoteService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public List<Note> listNotes() {
        return noteService.findAll();
    }
}
