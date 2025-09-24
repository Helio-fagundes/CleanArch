package io.meuprojeto.clean_arch_demo.adapters.controller;

import io.meuprojeto.clean_arch_demo.usecase.createUser.CreateUserUseCase;
import io.meuprojeto.clean_arch_demo.usecase.deleteUser.DeleteUserUseCase;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserRequestDto;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserResponseDto;
import io.meuprojeto.clean_arch_demo.usecase.listUsers.ListUsersUseCase;
import io.meuprojeto.clean_arch_demo.usecase.searchUser.SearchUserUseCase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUser;
    private final SearchUserUseCase searchUser;
    private final ListUsersUseCase listUsers;
    private final DeleteUserUseCase deleteUser;

    public UserController(CreateUserUseCase createUser,
                          SearchUserUseCase searchUser,
                          ListUsersUseCase listUsers,
                          DeleteUserUseCase deleteUser)
    {
        this.createUser = createUser;
        this.searchUser = searchUser;
        this.listUsers = listUsers;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    public UserResponseDto createNewUser(@Validated @RequestBody UserRequestDto request) {
        return createUser.execute(request);
    }

    @GetMapping("/{id}")
    public Optional<UserResponseDto> getUserById(@PathVariable Long id) {
        return searchUser.execute(id);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return listUsers.execute();
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return deleteUser.execute(id);
    }
}
