package com.example.newrsocketpractice.model;

import lombok.Data;

@Data
public class NoteDto {
    private String id;
    private String text;
    private String userId;
    private boolean deleted;

    public NoteDto(Note note, boolean deleted) {
        this.id = note.getId();
        this.text = note.getText();
        this.userId = note.getUserId();
        this.deleted = deleted;
    }
}
