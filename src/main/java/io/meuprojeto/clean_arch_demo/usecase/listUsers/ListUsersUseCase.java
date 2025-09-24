package io.meuprojeto.clean_arch_demo.usecase.listUsers;

import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;

import java.util.List;

public class ListUsersUseCase {

    private final UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> execute() {
        return userRepository.findAll()
                .stream()
                .map(User -> new UserResponseDto(User.getId(), User.getNome(), User.getEmail()))
                .toList();
    }
}
