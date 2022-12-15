package com.example.newrsocketpractice.controller;

import com.example.newrsocketpractice.model.User;
import com.example.newrsocketpractice.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Controller
public class CreateUserController {
    private final UserService userService;

    public CreateUserController(UserService userService) {
        this.userService = userService;
    }
    @MessageMapping("createUser")
    public Mono<Void> createUser(@RequestBody RequestUser requestUser){
        User user = new User();
        user.setUserName(requestUser.userName);
        user.setNotes(new ArrayList<>());
        userService.createUser(user);
        return Mono.empty();
    }

    public record RequestUser(String userName) {
    }
}
