package io.meuprojeto.clean_arch_demo.adapters.gateway;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.infrastructure.entity.UserEntity;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserRequestDto;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;

public class UserEntityMapper {

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(user);
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getNome(), userEntity.getEmail());
    }
}
