package io.meuprojeto.clean_arch_demo.usecase.deleteUser;

import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.NotExistUserException;

public class DeleteUserUseCase {

    private final UserRepository repository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public String execute(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new NotExistUserException("User with ID (" + id + ") does not exist.");
        }
        repository.deleteById(id);
        return "User with ID (" + id + ") has been deleted.";
    }
}
