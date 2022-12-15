package com.example.newrsocketpractice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "user")
public class User {
    private String id;
    private String userName;
    private List<Note> notes;
}
