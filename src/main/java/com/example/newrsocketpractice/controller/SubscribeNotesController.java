package com.example.newrsocketpractice.controller;

import com.example.newrsocketpractice.model.NoteDto;
import com.example.newrsocketpractice.model.User;
import com.example.newrsocketpractice.service.NoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

@Controller
public class SubscribeNotesController {
    private final NoteService noteService;

    public SubscribeNotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @MessageMapping("subscribe")

    public Flux<NoteDto> subscribe(@RequestBody User user) {
        return noteService.subscribeNotes(user.getId());
    }
}
