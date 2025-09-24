package io.meuprojeto.clean_arch_demo.infrastructure.entity;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    public UserEntity() {}

    public UserEntity(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }

    public User toDomain(){
        return new User(id, nome, email);
    }

    public UserResponseDto toDto(){
        return new UserResponseDto(id, nome, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
