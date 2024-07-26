package az.edu.turing.profileapp.controller;

import az.edu.turing.profileapp.model.dto.*;
import az.edu.turing.profileapp.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
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
    public ResponseEntity<RestResponse<Long>> createUser(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
        Long userId = userService.createUser(userCreateRequestDto);
        RestResponse<Long> response = RestResponse.<Long>builder()
                .data(userId)
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponseDto>> getUserById(@PathVariable @NonNull Long id) {
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
    public ResponseEntity<Void> updateUser(@PathVariable @NonNull Long id, @RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(id, userUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NonNull Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateImage(@PathVariable @NonNull Long id, @RequestBody @Valid UpdateImageRequestDto updateImageRequestDto) {
        userService.updateImage(id, updateImageRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Long> getCountUsers() {
        return new ResponseEntity<>(userService.getCountUsers(), HttpStatus.OK);
    }
}

