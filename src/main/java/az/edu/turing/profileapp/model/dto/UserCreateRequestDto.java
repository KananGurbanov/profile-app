package az.edu.turing.profileapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
public record UserCreateRequestDto (
        @NotBlank
        String name,

        @NonNull
        Integer age,

        @NotBlank
        String image){
}
