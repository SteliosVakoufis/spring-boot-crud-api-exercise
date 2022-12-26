package com.api.exercise.restapiexercisespring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.exercise.restapiexercisespring.data.dtos.UserDTO;
import com.api.exercise.restapiexercisespring.data.repositories.UserRepository;
import com.api.exercise.restapiexercisespring.exceptions.UserAlreadyExistsException;
import com.api.exercise.restapiexercisespring.exceptions.UserNotFoundException;
import com.api.exercise.restapiexercisespring.util.UserEntityUtils;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserEntityUtils userEntityUtils;

    public UserService(UserRepository userRepository, UserEntityUtils userEntityUtils) {
        this.userRepository = userRepository;
        this.userEntityUtils = userEntityUtils;
    }

    public List<UserDTO> getAllUsers() {
        return userEntityUtils
                .allEntitiesToDTOs(userRepository.findAll());
    }

    public UserDTO getUser(Long id) throws UserNotFoundException {
        var result = userRepository.findById(id);

        if (result.isPresent()) {
            return userEntityUtils.entityToDTO(result.get());
        }

        throw new UserNotFoundException("User with id (%d) not found, Please try again.".formatted(id));
    }

    public UserDTO addUser(UserDTO dto) throws UserAlreadyExistsException {
        try {
            return userEntityUtils
                    .entityToDTO(userRepository
                            .save(userEntityUtils
                                    .dtoToEntity(dto)));
        } catch (Exception e) {
            throw new UserAlreadyExistsException("User already exists with this information, Please try again.");
        }
    }

    public UserDTO updateUser(Long id, UserDTO dto) throws UserNotFoundException, UserAlreadyExistsException {
        var user = userRepository.findById(id);
        if (user.isPresent()) {

            if (dto.getEmail() != null)
                user.get().setEmail(dto.getEmail());
            if (dto.getUsername() != null)
                user.get().setUsername(dto.getUsername());
            if (dto.getFirstName() != null)
                user.get().setFirstName(dto.getFirstName());
            if (dto.getLastName() != null)
                user.get().setLastName(dto.getLastName());

            try {
                return userEntityUtils.entityToDTO(
                        userRepository.save(user.get()));
            } catch (Exception e) {
                throw new UserAlreadyExistsException("User already exists with this information, Please try again.");
            }
        }

        throw new UserNotFoundException("User with id (%d) not found, Please try again.".formatted(id));
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException("The user you tried to delete does not exist, Please try again.");
        }
    }
}
