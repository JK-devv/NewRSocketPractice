package com.example.newrsocketpractice.service;

import com.example.newrsocketpractice.model.Note;
import com.example.newrsocketpractice.model.NoteDto;
import com.example.newrsocketpractice.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NoteService {
    private final Sinks.Many<NoteDto> notes = Sinks.many().multicast().directBestEffort();
    private final Flux<NoteDto> noteFlux = notes.asFlux().share();
    private final UserService userService;
    private final NoteRepository noteRepository;

    public NoteService(UserService userService, NoteRepository noteRepository) {
        this.userService = userService;
        this.noteRepository = noteRepository;
    }

    public void addNote(Note note) {
        noteRepository.save(note)
                .map(savedNote -> new NoteDto(savedNote, false))
                .subscribe(notes::tryEmitNext);
    }

    public void deleteNote(String userId, String noteId) {
        List<Note> usersNotes = new ArrayList<>();
        userService.findById(userId)
                .subscribe(user -> usersNotes.addAll(user.getNotes()));

        Note currentNote = usersNotes
                .stream()
                .filter(note -> note.getId().equals(noteId))
                .toList().get(0);
        noteRepository.delete(currentNote)
                .map(deletedNote -> new NoteDto(currentNote, true))
                .subscribe(notes::tryEmitNext);
    }

    public Flux<NoteDto> getNotes(String userId) {
        return noteRepository.findAllByUserId(userId)
                .map(note -> new NoteDto(note, false));
    }

    public Flux<NoteDto> subscribeNotes(String userId) {
        return noteFlux
                .filter(note -> note.getUserId().equals(userId))
                .doOnNext(note -> log.info("Notes stream for note {}", note))
                .doOnError(error -> log.error("Notes stream handling error", error))
                .doOnComplete(() -> log.info("Notes stream is completed"));
    }

}
