package az.edu.turing.profileapp.model.dto;

import az.edu.turing.profileapp.model.enums.SocialMediaType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProfileRequestDto(
        @NotBlank
        @Email
        String mailAddress,

        @NotNull
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "The password must be at least 8 characters long " +
                        "and can contain any of the specified character sets")
        String password,

        @NotNull
        SocialMediaType socialMediaType,

        @NotBlank
        String image) {
}
