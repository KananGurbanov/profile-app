package az.edu.turing.profileapp.service.impl;

import az.edu.turing.profileapp.dao.entity.UserEntity;
import az.edu.turing.profileapp.dao.repository.UserRepository;
import az.edu.turing.profileapp.exceptions.RecordNotFoundException;
import az.edu.turing.profileapp.mapper.UserMapper;
import az.edu.turing.profileapp.model.dto.UpdateImageRequestDto;
import az.edu.turing.profileapp.model.dto.UserCreateRequestDto;
import az.edu.turing.profileapp.model.dto.UserResponseDto;
import az.edu.turing.profileapp.model.dto.UserUpdateRequestDto;
import az.edu.turing.profileapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.edu.turing.profileapp.model.enums.Error.ERR_01;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Long createUser(UserCreateRequestDto userCreateRequestDto) {
        UserEntity savedUser = userRepository.save(userMapper.mapToEntity(userCreateRequestDto));
        return savedUser.getId();
    }

    @Override
    public void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        userEntity.setAge(userUpdateRequestDto.age());
        userEntity.setName(userUpdateRequestDto.name());
        userEntity.setImage(userUpdateRequestDto.image());
        userRepository.save(userEntity);
    }

    @Override
    public UserResponseDto retrieveUser(Long id) {
        return userMapper.mapToResponseDto(userRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription())));
    }

    @Override
    public List<UserResponseDto> retrieveUsers() {
        return userMapper.mapToResponseDtoList(userRepository.findAll());
    }

    @Override
    public void updateImage(Long id, UpdateImageRequestDto updateImageRequestDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        userEntity.setImage(updateImageRequestDto.image());
        userRepository.save(userEntity);
    }

    @Override
    public Long getCountUsers() {
        return userRepository.count();
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription());
        }
    }
}
