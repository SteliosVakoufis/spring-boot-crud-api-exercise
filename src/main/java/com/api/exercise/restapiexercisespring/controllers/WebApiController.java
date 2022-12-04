package com.api.exercise.restapiexercisespring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.exercise.restapiexercisespring.data.dtos.UserDTO;
import com.api.exercise.restapiexercisespring.services.UserService;

@RestController
@RequestMapping("/api/user")
public class WebApiController {
    private final UserService userService;

    public WebApiController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable(name = "id", required = true) Long id){
        this.userService.deleteUser(id);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDTO updateUser(@PathVariable(name = "id", required = true) Long id, @RequestBody UserDTO dto){
        return this.userService.updateUser(id, dto);
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserDTO dto){
        return this.userService.addUser(dto);
    }

    @GetMapping(value="/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO getUser(@PathVariable(name = "id") Long id) {
        return this.userService.getUser(id);
    }
}
