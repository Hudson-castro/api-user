package br.com.invest.api.user_api.dto;

import br.com.invest.api.user_api.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Boolean active,
        LocalDateTime createdAt
) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getActive(),
                user.getCreatedAt()
        );
    }

    public static List<UserResponse> fromEntities(List<User> users) {
        return users.stream()
                .map(UserResponse::fromEntity)
                .toList();
    }
}

