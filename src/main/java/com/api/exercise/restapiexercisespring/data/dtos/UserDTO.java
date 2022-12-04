package com.api.exercise.restapiexercisespring.data.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
