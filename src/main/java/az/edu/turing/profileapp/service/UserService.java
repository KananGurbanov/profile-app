package az.edu.turing.profileapp.service;

import az.edu.turing.profileapp.model.dto.*;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserCreateRequestDto userCreateRequestDto);
    void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);
    UserResponseDto retrieveUser(Long id);
    List<UserResponseDto> retrieveUsers();
    void updateImage(Long id, UpdateImageRequestDto updateImageRequestDto);
    Long getCountUsers();
    void deleteUser(Long id);
}
