package com.api.exercise.restapiexercisespring.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.exercise.restapiexercisespring.data.dtos.UserDTO;
import com.api.exercise.restapiexercisespring.data.entities.UserEntity;

@Component
public class UserEntityUtils {
    public UserEntity dtoToEntity(UserDTO dto) {
        var result = new UserEntity();
        result.setEmail(dto.getEmail());
        result.setUsername(dto.getUsername());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());

        return result;
    }

    public UserDTO entityToDTO(UserEntity entity){
        var result = new UserDTO();
        result.setEmail(entity.getEmail());
        result.setUsername(entity.getUsername());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());

        return result;
    }

    public List<UserDTO> allEntitiesToDTOs(Iterable<UserEntity> entities){
        var result = new ArrayList<UserDTO>();
        entities.forEach(
            entity -> result.add(this.entityToDTO(entity))
        );

        return result;
    }
}
