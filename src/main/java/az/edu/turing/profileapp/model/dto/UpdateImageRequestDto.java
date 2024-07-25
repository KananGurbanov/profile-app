package az.edu.turing.profileapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateImageRequestDto(
        @NotBlank
        String image) {
}
