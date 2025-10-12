package io.meuprojeto.clean_arch_demo.adapters.gateway;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.infrastructure.entity.UserEntity;
import io.meuprojeto.clean_arch_demo.infrastructure.repository.JpaUserCrudRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositoryJpa implements UserRepository {

    private final JpaUserCrudRepository jpaRepository;

    public UserRepositoryJpa(JpaUserCrudRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = jpaRepository.save(UserEntityMapper.toUserEntity(user));
        return UserEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> entities = jpaRepository.findAll();
        return entities.stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserEntityMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
