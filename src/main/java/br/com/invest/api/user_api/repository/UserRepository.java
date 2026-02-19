package br.com.invest.api.user_api.repository;


import br.com.invest.api.user_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email); //Contrato  para verificar se existe o email

    boolean existsByEmail(String email);
}