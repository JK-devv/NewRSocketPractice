package com.example.newrsocketpractice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "notes")
public class Note {
    @Id
    private String id;
    private String text;
    private String userId;
}
