package br.com.invest.api.user_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank(message = "Hudson")
    private String name;


    @NotBlank(message = "hudsoncastro24@gmail.com")
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
