package com.example.newrsocketpractice.repository;

import com.example.newrsocketpractice.model.Note;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note, String> {
    Flux<Note> findAllByUserId(String userId);
}
