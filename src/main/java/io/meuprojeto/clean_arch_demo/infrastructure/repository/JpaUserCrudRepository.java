package io.meuprojeto.clean_arch_demo.infrastructure.repository;

import io.meuprojeto.clean_arch_demo.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserCrudRepository extends JpaRepository<UserEntity, Long> {}
