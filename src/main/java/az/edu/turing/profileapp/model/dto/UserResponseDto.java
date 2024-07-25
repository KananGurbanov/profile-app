package az.edu.turing.profileapp.model.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String name,
        Integer age,
        String image) {
}
