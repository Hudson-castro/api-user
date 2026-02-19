package br.com.invest.api.user_api.mapper;

import br.com.invest.api.user_api.dto.UserResponse;
import br.com.invest.api.user_api.entity.User;

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
