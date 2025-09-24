package io.meuprojeto.clean_arch_demo.domain.model;


public class User {

    private final Long id;
    private final String nome;
    private final String email;

    public User(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        validate();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void validate() {
        nameIsValid(this.nome);
        emailIsValid(this.email);
    }

    public void nameIsValid(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("the name is required");
        }
    }

    public void emailIsValid(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("the email is required");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("the email is invalid");
        }
    }
}
