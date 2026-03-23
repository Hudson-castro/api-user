package br.com.invest.api.user.mapper;

import br.com.invest.api.user.dto.UserResponse;
import br.com.invest.api.user.entity.User;

import java.util.List;

public class UserMapper {


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
                .map(UserMapper::fromEntity)
                .toList();
    }

}
