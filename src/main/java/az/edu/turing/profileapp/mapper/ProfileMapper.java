package az.edu.turing.profileapp.mapper;

import az.edu.turing.profileapp.dao.entity.ProfileEntity;
import az.edu.turing.profileapp.model.dto.ProfileRequestDto;
import az.edu.turing.profileapp.model.dto.ProfileResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    ProfileEntity maptoEntity(ProfileRequestDto profile);
    ProfileResponseDto maptoDto(ProfileEntity profile);
    List<ProfileEntity> maptoEntities(List<ProfileRequestDto> profiles);
    List<ProfileResponseDto> maptoDtos(List<ProfileEntity> profiles);
}
