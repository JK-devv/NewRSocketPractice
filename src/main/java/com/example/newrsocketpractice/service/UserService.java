package com.example.newrsocketpractice.service;
import com.example.newrsocketpractice.model.User;
import com.example.newrsocketpractice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user)
                .doFirst(() -> log.info(String.format("User: %s, successfully created", user)))
                .subscribe();
    }

    public Mono<User> findById(String userId) {
        return userRepository.findById(userId);
    }
}
