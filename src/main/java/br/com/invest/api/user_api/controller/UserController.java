package br.com.invest.api.user_api.controller;

import br.com.invest.api.user_api.dto.CreateUserRequest;
import br.com.invest.api.user_api.dto.UpdateUserRequest;
import br.com.invest.api.user_api.dto.UserResponse;
import br.com.invest.api.user_api.entity.User;
import br.com.invest.api.user_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @RequestBody @Valid CreateUserRequest request
    ) {
        User user = service.create(
                request.name(),
                request.email(),
                request.password()
        );

        URI location = URI.create("/users/" + user.getId());

        return ResponseEntity
                .created(location)
                .body(UserResponse.fromEntity(user));
    }


    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        List<UserResponse> users = service.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable UUID id
    ) {
        User user = service.findById(id);
        return ResponseEntity.ok(UserResponse.fromEntity(user));
    }



    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        User user = service.update(
                id,
                request.name(),
                request.email()
        );

        return ResponseEntity.ok(UserResponse.fromEntity(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}