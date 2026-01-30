package br.com.invest.api.user_api.entity;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "Users") //Controle explícito
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id; //Para evitar enumeraÇão de IDS

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    protected User() {

        //Construtor protegido exigido pelo JPA
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
