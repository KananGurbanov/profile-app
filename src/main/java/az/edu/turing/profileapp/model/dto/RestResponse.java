package az.edu.turing.profileapp.model.dto;

import lombok.Builder;

@Builder
public record RestResponse<T> (String status, T data) {
}
