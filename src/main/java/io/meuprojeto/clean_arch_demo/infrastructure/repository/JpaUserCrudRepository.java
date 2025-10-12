package io.meuprojeto.clean_arch_demo.infrastructure.repository;

import io.meuprojeto.clean_arch_demo.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserCrudRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
