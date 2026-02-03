package br.com.invest.api.user_api.exception;

public class EmailAlreadyInUseException  extends  RuntimeException{
public EmailAlreadyInUseException(String email){
    super("Email já está em uso: " + email);
}
}
