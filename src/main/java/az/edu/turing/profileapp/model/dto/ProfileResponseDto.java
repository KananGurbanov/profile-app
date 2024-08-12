package az.edu.turing.profileapp.model.dto;

import az.edu.turing.profileapp.model.enums.SocialMediaType;
import az.edu.turing.profileapp.model.enums.Status;

public record ProfileResponseDto(
        String mailAddress,
        String password,
        Status status,
        SocialMediaType socialMediaType,
        String image) {
}
