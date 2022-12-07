package com.api.exercise.restapiexercisespring.controller;

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
import com.api.exercise.restapiexercisespring.service.UserService;

@RestController
@RequestMapping("/api/user")
public class WebApiController {
    private final UserService userService;

    public WebApiController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a status code 200 if the user deletion is succesful.
     * @param id used to find the user.
     * @exception UserNotFoundException If user is not found.
     * @see {@link com.api.exercise.restapiexercisespring.exceptions.UserNotFoundException}
     */
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(name = "id", required = true) Long id){
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Accepts an id with a UserDTO Body to update a specific user
     * and then returns the updated user.
     * @param id    Used to update the specific user.
     * @param dto   Used to retrieve information about the user update.
     * @return      UserDTO in json format.
     * @throws UserAlreadyExistsException   If the user already exists.
     * @see {@link com.api.exercise.restapiexercisespring.exceptions.UserAlreadyExistsException} 
     * @throws UserNotFoundException        If the user is not found.
     * @see {@link com.api.exercise.restapiexercisespring.exceptions.UserNotFoundException}
     */
    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable(name = "id", required = true) Long id, @RequestBody UserDTO dto){
        try {
            return userService.updateUser(id, dto);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Accepts a UserDTO Body to add a new user, and then returns 
     * the new user that was added.
     * @param dto   Used to retrieve information about the user creation.
     * @return      UserDTO in json format.
     * @throws UserAlreadyExistsException   If the user already exists.
     * @see {@link com.api.exercise.restapiexercisespring.exceptions.UserAlreadyExistsException} 
     */
    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserDTO dto){
        try {
            return userService.addUser(dto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Returns all the users that exist.
     * @return      List<UserDTO> in json format
     */
    @GetMapping(value="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Returns a UserDTO in JSON format by the id specified.
     * @param id    Used to retrieve information about the user.
     * @return      UserDTO in JSON format.
     * @throws UserNotFoundException        If the user is not found.
     * @see {@link com.api.exercise.restapiexercisespring.exceptions.UserNotFoundException}
     */
    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable(name = "id") Long id) {
        try {
            return userService.getUser(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
