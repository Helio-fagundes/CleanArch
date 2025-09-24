package io.meuprojeto.clean_arch_demo.usecase.searchUser;

import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;

import java.util.Optional;

public class SearchUserUseCase {

    private final UserRepository userRepository;

    public SearchUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDto> execute(Long  id) {
        return userRepository.findById(id)
                .map(User -> new UserResponseDto(User.getId(), User.getNome(), User.getEmail()));
    }
}
