package br.com.invest.api.user_api.service;

import br.com.invest.api.user_api.entity.User;
import br.com.invest.api.user_api.exception.EmailAlreadyInUseException;
import br.com.invest.api.user_api.exception.UserNotFoundException;
import br.com.invest.api.user_api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String name, String email, String rawPassword) {
        log.info("Criando usuário | email={}", email);

        validateEmailUniqueness(email);

        String hashedPassword = passwordEncoder.encode(rawPassword);

        User user = new User(name, email, hashedPassword);
        User savedUser = repository.save(user);

        log.info("Usuário criado com sucesso | id={}", savedUser.getId());
        return savedUser;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.debug("Listando todos os usuários");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(UUID id) {
        log.debug("Buscando usuário | id={}", id);
        return getUserOrThrow(id);
    }

    @Transactional
    public User update(UUID id, String name, String email) {
        log.info("Atualizando usuário | id={}", id);

        User user = getUserOrThrow(id);

        if (!user.getEmail().equalsIgnoreCase(email)) {
            validateEmailUniqueness(email);
            user.setEmail(email);
        }

        user.setName(name);

        User updatedUser = repository.save(user);
        log.info("Usuário atualizado com sucesso | id={}", updatedUser.getId());

        return updatedUser;

    }

    public void delete(UUID id) {
        log.info("Removendo usuário | id={}", id);

        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        repository.deleteById(id);
        log.info("Usuário removido com sucesso | id={}", id);
    }

    private User getUserOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado | id={}", id);
                    return new UserNotFoundException(id);
                });
    }

    private void validateEmailUniqueness(String email) {
        if (repository.existsByEmail(email)) {
            log.warn("Email já em uso | email={}", email);
            throw new EmailAlreadyInUseException(email);
        }
    }
}