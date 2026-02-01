package br.com.invest.api.user_api.controller;

import br.com.invest.api.user_api.dto.CreateUserRequest;
import br.com.invest.api.user_api.dto.UserResponse;
import br.com.invest.api.user_api.entity.User;
import br.com.invest.api.user_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) {

        User user = service.create(request.getName(), request.getEmail());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());

    }

    @GetMapping
    public List<UserResponse> list() {
        return service.findAll()
                .stream()
                .map(user -> new UserResponse(user.getId(),
                        user.getName(),
                        user.getEmail()))
                .toList();
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable UUID id) {

        User user = service.finById(id);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }

}
