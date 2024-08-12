package az.edu.turing.profileapp.mapper;

import az.edu.turing.profileapp.dao.entity.UserEntity;
import az.edu.turing.profileapp.model.dto.UserRequestDto;
import az.edu.turing.profileapp.model.dto.UserResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, unmappedSourcePolicy = IGNORE)
public interface UserMapper {
    UserEntity mapToEntity(UserRequestDto userCreateRequestDto);
    UserResponseDto mapToResponseDto(UserEntity userEntity);
    List<UserResponseDto> mapToResponseDtoList(List<UserEntity> userEntities);
}
