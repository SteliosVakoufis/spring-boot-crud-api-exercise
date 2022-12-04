package com.api.exercise.restapiexercisespring.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.api.exercise.restapiexercisespring.data.entities.UserEntity;
import com.api.exercise.restapiexercisespring.data.repositories.UserRepository;

import lombok.var;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    public AppStartupEvent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var userEntity = new UserEntity();
        userEntity.setUsername("test-user");
        userEntity.setEmail("test.user@test.org");

        try {
            userRepository.save(userEntity);
        } catch (Exception e){
            System.err.printf("User %s already exists!\n", userEntity);
        }
    }
    
}
