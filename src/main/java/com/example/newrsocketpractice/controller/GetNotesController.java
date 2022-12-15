package com.example.newrsocketpractice.controller;

import com.example.newrsocketpractice.model.NoteDto;
import com.example.newrsocketpractice.model.User;
import com.example.newrsocketpractice.service.NoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

@Controller
public class GetNotesController {
    private final NoteService noteService;

    public GetNotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @MessageMapping("getNotes")
    public Flux<NoteDto> getNotes(@RequestBody User user) {
        return noteService.getNotes(user.getId());
    }
}
