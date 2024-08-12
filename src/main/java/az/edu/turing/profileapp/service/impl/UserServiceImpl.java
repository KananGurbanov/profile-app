package az.edu.turing.profileapp.service.impl;

import az.edu.turing.profileapp.dao.entity.ProfileEntity;
import az.edu.turing.profileapp.dao.entity.UserEntity;
import az.edu.turing.profileapp.dao.repository.ProfileRepository;
import az.edu.turing.profileapp.dao.repository.UserRepository;
import az.edu.turing.profileapp.exceptions.BadRequestException;
import az.edu.turing.profileapp.exceptions.RecordNotFoundException;
import az.edu.turing.profileapp.mapper.ProfileMapper;
import az.edu.turing.profileapp.mapper.UserMapper;
import az.edu.turing.profileapp.model.dto.*;
import az.edu.turing.profileapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.edu.turing.profileapp.model.enums.Error.ERR_01;
import static az.edu.turing.profileapp.model.enums.Error.ERR_02;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;

    @Transactional
    @Override
    public Long createUser(UserRequestDto userCreateRequestDto) {
        if (userCreateRequestDto.profiles().stream()
                .anyMatch(profileRequestDto -> profileRepository.existsByMailAddressOrPassword(
                        profileRequestDto.mailAddress(),
                        profileRequestDto.password()))) {
            throw new BadRequestException(ERR_02.getErrorCode(), ERR_02.getErrorDescription());
        }
        log.info("Creating user {}", userCreateRequestDto);
        UserEntity savedUser = userRepository.save(userMapper.mapToEntity(userCreateRequestDto));
        return savedUser.getId();
    }

    @Transactional
    @Override
    public void updateUser(Long id, UserRequestDto userUpdateRequestDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        log.info("User changed from {} to {}", userEntity, userUpdateRequestDto);
        userEntity.setAge(userUpdateRequestDto.age());
        userEntity.setName(userUpdateRequestDto.name());
        userEntity.setProfiles(profileMapper.maptoEntities(userUpdateRequestDto.profiles()));
        userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto retrieveUser(Long id) {
        return userMapper.mapToResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription())));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> retrieveUsers() {
        return userMapper.mapToResponseDtoList(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountUsers() {
        long count = userRepository.count();
        log.info("Number of users: {}", userRepository.count());
        return count;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            log.error("Failed to delete user: User with ID {} not found", id);
            throw new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription());
        }
        userRepository.deleteById(id);
        log.info("User with ID {} deleted successfully", id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long addProfile(Long userId, ProfileRequestDto profileRequestDto) {
        if (profileRepository.existsByMailAddressOrPassword(profileRequestDto.mailAddress(),
                profileRequestDto.password())) {
            throw new BadRequestException(ERR_02.getErrorCode(), ERR_02.getErrorDescription());
        }
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        ProfileEntity profileEntity = profileMapper.maptoEntity(profileRequestDto);
        userEntity.getProfiles().add(profileEntity);
        userRepository.save(userEntity);
        return profileEntity.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileResponseDto retrieveProfile(Long userId, Long id) {
        ProfileEntity profileEntity1 = profileRepository.findByUserIdAndProfileId(userId, id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        log.info("Retrieving profile {}", profileEntity1);
        return profileMapper.maptoDto(profileEntity1);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfileResponseDto> retrieveProfiles(Long userId) {
        return profileMapper.maptoDtos(profileRepository.findByUserId(userId));
    }

    @Transactional
    @Override
    public void deleteProfile(Long userId, Long id) {
        ProfileEntity profileEntity = profileRepository.findByUserIdAndProfileId(userId, id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        log.info("Deleting profile {}", profileEntity);
        profileRepository.delete(profileEntity);
    }

    @Transactional
    @Override
    public void updateProfile(Long userId, Long id, ProfileRequestDto profileRequestDto) {
        ProfileEntity profileEntity = profileRepository.findByUserIdAndProfileId(userId, id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        log.info("Updating profile from {} to {}", profileEntity, profileRequestDto);
        profileEntity.setMailAddress(profileRequestDto.mailAddress());
        profileEntity.setPassword(profileRequestDto.password());
        profileEntity.setImage(profileRequestDto.image());
        profileEntity.setSocialMediaType(profileRequestDto.socialMediaType());
        profileRepository.save(profileEntity);
    }

    @Transactional
    @Override
    public void updateStatus(Long userId, Long id, UpdateStatusRequestDto statusRequestDto) {
        ProfileEntity profileEntity = profileRepository.findByUserIdAndProfileId(userId, id)
                .orElseThrow(() -> new RecordNotFoundException(ERR_01.getErrorCode(), ERR_01.getErrorDescription()));
        log.info("Updating status from {} to {}", profileEntity.getStatus(), statusRequestDto.status());
        profileEntity.setStatus(statusRequestDto.status());
        profileRepository.save(profileEntity);
    }
}
