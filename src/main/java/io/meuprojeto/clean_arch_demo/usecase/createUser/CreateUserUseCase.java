package io.meuprojeto.clean_arch_demo.usecase.createUser;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserRequestDto;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;


public class CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserResponseDto execute(UserRequestDto dto) {
        User user = new User(null, dto.getNome(), dto.getEmail());
        User savedUser = repository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getNome(), savedUser.getEmail());
    }
}
