package az.edu.turing.profileapp.controller;

import az.edu.turing.profileapp.model.dto.*;
import az.edu.turing.profileapp.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Validated
@RequiredArgsConstructor
@Tag(name = "User Controller API", description = "user controller")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<RestResponse<Long>> createUser(@RequestBody @Valid UserRequestDto userCreateRequestDto) {
        Long userId = userService.createUser(userCreateRequestDto);
        RestResponse<Long> response = RestResponse.<Long>builder()
                .data(userId)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponseDto>> getUserById(@PathVariable @NotNull Long id) {
        UserResponseDto userResponseDto = userService.retrieveUser(id);
        RestResponse<UserResponseDto> response = RestResponse.<UserResponseDto>builder()
                .data(userResponseDto)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> users = userService.retrieveUsers();
        RestResponse<List<UserResponseDto>> response = RestResponse.<List<UserResponseDto>>builder()
                .data(users)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable @NotNull Long id, @RequestBody @Valid UserRequestDto userUpdateRequestDto) {
        userService.updateUser(id, userUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NotNull Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCountUsers() {
        return new ResponseEntity<>(userService.getCountUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/profiles")
    public ResponseEntity<RestResponse<List<ProfileResponseDto>>> getAllProfiles(@PathVariable @NotNull Long id) {
        List<ProfileResponseDto> profileResponseDtos = userService.retrieveProfiles(id);
        RestResponse<List<ProfileResponseDto>> response = RestResponse.<List<ProfileResponseDto>>builder()
                .data(profileResponseDtos)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{id}/profiles")
    public ResponseEntity<RestResponse<Long>> addProfile(@PathVariable @NotNull Long id, @RequestBody @Valid ProfileRequestDto profileRequestDto) {
        Long profileId = userService.addProfile(id, profileRequestDto);
        RestResponse<Long> response = RestResponse.<Long>builder()
                .data(profileId)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/profiles/{profileId}")
    public ResponseEntity<RestResponse<ProfileResponseDto>> retrieveProfile(@PathVariable @NotNull Long id, @PathVariable @NotNull Long profileId) {
        ProfileResponseDto profileResponseDto = userService.retrieveProfile(id, profileId);
        RestResponse<ProfileResponseDto> response = RestResponse.<ProfileResponseDto>builder()
                .data(profileResponseDto)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/profiles/{profileId}")
    public ResponseEntity<Void> updateProfile(@PathVariable @NotNull Long id, @PathVariable @NotNull Long profileId, @RequestBody @Valid ProfileRequestDto profileRequestDto) {
        userService.updateProfile(id, profileId,  profileRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/profiles/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable @NotNull Long id, @PathVariable @NotNull Long profileId) {
        userService.deleteProfile(id, profileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/profiles/{profileId}")
    public ResponseEntity<Void> updateStatus(@PathVariable @NotNull Long id, @PathVariable @NotNull Long profileId, @RequestBody @Valid UpdateStatusRequestDto updateStatusRequestDto) {
        userService.updateStatus(id, profileId, updateStatusRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
