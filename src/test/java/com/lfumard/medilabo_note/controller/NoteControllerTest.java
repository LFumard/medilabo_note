package com.lfumard.medilabo_note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfumard.medilabo_note.model.Note;
import com.lfumard.medilabo_note.service.NoteService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

//import static org.assertj.core.configuration.Services.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testGetNoteById() throws Exception {

        Note note = new Note("1", 1L, "Note1 Patient 1");
        given(noteService.findById("1")).willReturn(note);

        mockMvc.perform(get("/service_note/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientNote").value("Note1 Patient 1"));

    }

    @Test
    public void testGetNoteByPatientId() throws Exception {

        List<Note> noteListAll = new ArrayList<>();
        noteListAll.add(new Note("1", 1L, "Note1 Patient 1"));
        noteListAll.add(new Note("2", 1L, "Note2 Patient 1"));
        given(noteService.findAllNoteByPatientId(1L)).willReturn(noteListAll);

        mockMvc.perform(get("/service_note/list/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientNote").value("Note1 Patient 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].patientId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].patientNote").value("Note2 Patient 1"));

    }

    @Test
    public void testAddNote() throws Exception {

        Note note = new Note("1", 1L, "Note1 Patient 1");
        String strContent = objectMapper.writeValueAsString(note);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/service_note/addNote")
                        .contentType(APPLICATION_JSON)
                        .content(strContent))
                .andExpect(status().isOk());

        verify(noteService, times(1)).add(any());
    }

    @Test
    public void testUpdateNote() throws Exception {

        Note note = new Note("1", 1L, "Note1 Patient 1");
        String strContent = objectMapper.writeValueAsString(note);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/service_note/update")
                        .contentType(APPLICATION_JSON)
                        .content(strContent))
                .andExpect(status().isOk());

        verify(noteService, times(1)).update(any());
    }

    @Test
    public void testDeleteNoteById() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/service_note/delete/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService, times(1)).deleteById(anyString());
    }

    @Test
    public void testDeleteAllNoteByPatientId() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/service_note/deleteNoteByPatientId/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService, times(1)).deleteAllNoteByPatient(anyLong());
    }
}
