package io.meuprojeto.clean_arch_demo.usecase.searchUser;

import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.NotExistUserException;

import java.util.Optional;

public class SearchUserUseCase {

    private final UserRepository userRepository;

    public SearchUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDto> execute(Long  id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if (userRepository.findById(id).isEmpty()) {
            throw new NotExistUserException("User with ID (" + id + ") does not exist.");
        }
        return userRepository.findById(id)
                .map(User -> new UserResponseDto(User.getId(), User.getNome(), User.getEmail()));
    }
}
