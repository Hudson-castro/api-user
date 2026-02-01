package br.com.invest.api.user_api.service;

import br.com.invest.api.user_api.entity.User;
import br.com.invest.api.user_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;

    }
    public User create(String name, String email){
        if(repository.existsByEmail(email)){
            throw new IllegalArgumentException("Email já Cadastrado");

        }
        User user = new User (name, email);
        return repository.save(user);

    }
    public List<User> findAll(){
        return repository.findAll();
    }

 public User finById(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado "));
 }
}
