package az.edu.turing.profileapp.mapper;

import az.edu.turing.profileapp.dao.entity.UserEntity;
import az.edu.turing.profileapp.model.dto.UserCreateRequestDto;
import az.edu.turing.profileapp.model.dto.UserResponseDto;
import az.edu.turing.profileapp.model.dto.UserUpdateRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, unmappedSourcePolicy = IGNORE)
public interface UserMapper {
    UserEntity mapToEntity(UserCreateRequestDto userCreateRequestDto);
    UserResponseDto mapToResponseDto(UserEntity userEntity);
    UserEntity mapToEntity(UserUpdateRequestDto userUpdateRequestDto);
    List<UserResponseDto> mapToResponseDtoList(List<UserEntity> userEntities);
}
