package com.example.newrsocketpractice.controller;

import com.example.newrsocketpractice.model.User;
import com.example.newrsocketpractice.service.NoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Controller
public class DeleteNoteController {
    private final NoteService noteService;

    public DeleteNoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @MessageMapping("deleteNote")
    public Mono<Void> deleteNote(@RequestBody DeleteNoteRequest request) {
        noteService.deleteNote(request.user.getId(), request.id);
        return Mono.empty();
    }

    public record DeleteNoteRequest(User user, String id) {
    }
}
