package io.meuprojeto.clean_arch_demo.adapters.mapper;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserRequestDto;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;

public class UserDtoMapper {

    public static User toDomain(UserRequestDto userRequestDto) {
        return new User(null, userRequestDto.getNome(), userRequestDto.getEmail());
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getNome(), user.getEmail());
    }
}
