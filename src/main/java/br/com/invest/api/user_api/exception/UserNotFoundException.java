package br.com.invest.api.user_api.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID id){
        super("Usuário não encontrado. ID: " + id);
    }


}
