package com.lfumard.medilabo_note.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "Patient_notes")
public class Note {

    @Id
    private String id;
    private long patientId;
    private LocalDateTime date;
    private String patientNote;

    public Note(String id, long patientId, String patientNote) {
        this.id = id;
        this.patientId = patientId;
        this.date = getDate();
        this.patientNote = patientNote;
    }

    public Note() {
        this.date = getDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(String patientNote) {
        this.patientNote = patientNote;
    }
}