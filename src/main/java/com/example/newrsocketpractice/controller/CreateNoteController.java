package com.example.newrsocketpractice.controller;

import com.example.newrsocketpractice.model.Note;
import com.example.newrsocketpractice.model.User;
import com.example.newrsocketpractice.service.NoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;


@Controller
public class CreateNoteController {
    private final NoteService noteService;

    public CreateNoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @MessageMapping("createNote")
    public Mono<Void> createNote(@RequestBody CreateNoteRequest request) {
        Note note = new Note();
        note.setText(request.text);
        note.setUserId(request.user.getId());
        noteService.addNote(note);
        return Mono.empty();
    }

    public record CreateNoteRequest(String text, User user) {

    }
}
