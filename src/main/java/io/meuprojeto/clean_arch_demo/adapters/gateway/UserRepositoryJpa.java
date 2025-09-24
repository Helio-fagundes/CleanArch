package io.meuprojeto.clean_arch_demo.adapters.gateway;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.infrastructure.entity.UserEntity;
import io.meuprojeto.clean_arch_demo.infrastructure.repository.JpaUserCrudRepository;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class UserRepositoryJpa implements UserRepository {

    private final JpaUserCrudRepository jpaRepository;

    public UserRepositoryJpa(JpaUserCrudRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = jpaRepository.save(new UserEntity(user));
        return entity.toDomain();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> entities = jpaRepository.findAll();
        return entities.stream().map(UserEntity::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
