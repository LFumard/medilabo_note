package com.lfumard.medilabo_note.service;

import com.lfumard.medilabo_note.model.Note;
import com.lfumard.medilabo_note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.AssertEquals.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Switch.CaseOperator.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void testFindAll() {

        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note("1", 1L, "Note1 Patient 1"));
        noteList.add(new Note("2", 1L, "Note2 Patient 1"));
        noteList.add(new Note("3", 2L, "Note3 Patient 2"));
        Mockito.when(noteRepository.findAll()).thenReturn(noteList);

        List<Note> noteListResult = noteService.findAll();

        assertEquals(noteList, noteListResult);
        verify(noteRepository, times(1)).findAll();
        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void testFindById() {

        Note note = new Note("1", 1L, "Note1 Patient 1");

        Mockito.when(noteRepository.findById("1")).thenReturn(Optional.of(note));
        Note noteResult = noteService.findById("1");

        assertEquals(note, noteResult);
        verify(noteRepository, times(1)).findById(anyString());
        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void testFindAllNoteByPatientId() {

        List<Note> noteListAll = new ArrayList<>();
        noteListAll.add(new Note("1", 1L, "Note1 Patient 1"));
        noteListAll.add(new Note("2", 1L, "Note2 Patient 1"));

        Mockito.when(noteRepository.findAllByPatientId(1L)).thenReturn(noteListAll);
        List<Note> listNoteResult = noteService.findAllNoteByPatientId(1L);

        assertEquals(noteListAll, listNoteResult);
        verify(noteRepository, times(1)).findAllByPatientId(anyLong());
        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void testAdd() {

        Note note = new Note("1", 1L, "Note1 Patient 1");
        LocalDateTime noteDate = LocalDateTime.now();

        noteService.add(note);

        assertEquals(noteDate, note.getDate());
        verify(noteRepository, times(1)).save(note);
        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void testUpdate() {

        Note note = new Note("1", 1L, "Note1 Patient 1");
        LocalDateTime noteDate = LocalDateTime.now();

        noteService.update(note);

        assertNotEquals(noteDate, note.getDate());
        verify(noteRepository, times(1)).save(note);
        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void testDeleteById() {

        Note note = new Note("1", 1L, "Note1 Patient 1");

        noteService.deleteById("1");

        verify(noteRepository, times(1)).deleteById(anyString());
        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void testDeleteAllNoteByPatientId() {

        List<Note> noteListAll = new ArrayList<>();
        noteListAll.add(new Note("1", 1L, "Note1 Patient 1"));
        noteListAll.add(new Note("2", 1L, "Note2 Patient 1"));

        Mockito.when(noteRepository.findAllByPatientId(1L)).thenReturn(noteListAll);
        noteService.deleteAllNoteByPatient(1L);

        verify(noteRepository, times(1)).findAllByPatientId(anyLong());
        verify(noteRepository, times(noteListAll.size())).deleteById(anyString());
        verifyNoMoreInteractions(noteRepository);
    }
}
