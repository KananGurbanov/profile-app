package az.edu.turing.profileapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record UserUpdateRequestDto(
        @NotBlank
        String name,

        @NonNull
        Integer age,

        @NotBlank
        String image) {
}
