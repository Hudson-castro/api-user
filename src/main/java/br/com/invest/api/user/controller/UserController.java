package br.com.invest.api.user.controller;

import br.com.invest.api.user.dto.CreateUserRequest;
import br.com.invest.api.user.dto.UpdateUserRequest;
import br.com.invest.api.user.dto.UserResponse;
import br.com.invest.api.user.entity.User;
import br.com.invest.api.user.mapper.UserMapper;
import br.com.invest.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(
            @RequestBody @Valid CreateUserRequest request
    ) {
        User user = service.create(
                request.name(),
                request.email(),
                request.password()
        );

        return UserMapper.fromEntity(user);
    }

    @GetMapping
    public List<UserResponse> list() {
        List<UserResponse> users = service.findAll()
                .stream()
                .map(UserMapper::fromEntity)
                .toList();
        return users;
    }

    @GetMapping("/{id}")
    public UserResponse findById(
            @PathVariable UUID id
    ) {
        User user = service.findById(id);
        return UserMapper.fromEntity(user);
    }

    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        User user = service.update(
                id,
                request.name(),
                request.email()
        );

        return UserMapper.fromEntity(user);
    }

   @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id
    ) {
        service.delete(id);
    }
}