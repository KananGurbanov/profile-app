package az.edu.turing.profileapp.service;

import az.edu.turing.profileapp.model.dto.*;

import java.util.List;

public interface UserService {
    Long createUser(UserRequestDto userCreateRequestDto);
    void updateUser(Long id, UserRequestDto userUpdateRequestDto);
    UserResponseDto retrieveUser(Long id);
    List<UserResponseDto> retrieveUsers();
    Long getCountUsers();
    void deleteUser(Long id);
    Long addProfile(Long userId, ProfileRequestDto profileRequestDto);
    ProfileResponseDto retrieveProfile(Long userId, Long id);
    List<ProfileResponseDto> retrieveProfiles(Long userId);
    void deleteProfile(Long userId, Long id);
    void updateProfile(Long userId, Long id, ProfileRequestDto profileRequestDto);
    void updateStatus(Long userId, Long id, UpdateStatusRequestDto statusRequestDto);
}
