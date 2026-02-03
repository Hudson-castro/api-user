package br.com.invest.api.user_api.controller;

import br.com.invest.api.user_api.dto.CreateUserRequest;
import br.com.invest.api.user_api.dto.UpdateUserRequest;
import br.com.invest.api.user_api.dto.UserResponse;
import br.com.invest.api.user_api.entity.User;
import br.com.invest.api.user_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) {
        User user = service.create(request.name(), request.email());
        return UserResponse.fromEntity(user);
    }

    // READ ALL
    @GetMapping
    public List<UserResponse> list() {
        return service.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .toList();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable UUID id) {
        User user = service.findById(id);
        return UserResponse.fromEntity(user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        User user = service.update(id, request.name(), request.email());
        return UserResponse.fromEntity(user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}