package io.meuprojeto.clean_arch_demo.usecase.deleteUser;

import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;

public class DeleteUserUseCase {

    private final UserRepository repository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public String execute(Long id) {
        repository.deleteById(id);
        return "User with ID " + id + " has been deleted.";
    }
}
