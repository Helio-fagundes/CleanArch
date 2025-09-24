package io.meuprojeto.clean_arch_demo.domain.repository;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);
}
