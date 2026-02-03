package br.com.invest.api.user_api.service;

import br.com.invest.api.user_api.entity.User;
import br.com.invest.api.user_api.exception.EmailAlreadyInUseException;
import br.com.invest.api.user_api.exception.UserNotFoundException;
import br.com.invest.api.user_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(String name, String email) {
        validateEmailUniqueness(email);

        User user = new User(name, email);
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(UUID id) {
        return getUserOrThrow(id);
    }

    public User update(UUID id, String name, String email) {
        User user = getUserOrThrow(id);

        if (!user.getEmail().equals(email)) {
            validateEmailUniqueness(email);
            user.setEmail(email);
        }

        user.setName(name);
        return repository.save(user);
    }

    public void delete(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        repository.delete(user);
    }

    private User getUserOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void validateEmailUniqueness(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }
    }
}