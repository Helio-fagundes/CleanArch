package io.meuprojeto.clean_arch_demo;

import io.meuprojeto.clean_arch_demo.adapters.gateway.UserRepositoryJpa;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.infrastructure.repository.JpaUserCrudRepository;
import io.meuprojeto.clean_arch_demo.usecase.createUser.CreateUserUseCase;
import io.meuprojeto.clean_arch_demo.usecase.deleteUser.DeleteUserUseCase;
import io.meuprojeto.clean_arch_demo.usecase.listUsers.ListUsersUseCase;
import io.meuprojeto.clean_arch_demo.usecase.searchUser.SearchUserUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CleanArchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanArchDemoApplication.class, args);
    }

    @Bean
    public UserRepository userRepository(JpaUserCrudRepository repo) {
        return new UserRepositoryJpa(repo);
    }

    @Bean
    public SearchUserUseCase searchUserUseCase(UserRepository repo) {
        return new SearchUserUseCase(repo);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(UserRepository repo) {
        return new ListUsersUseCase(repo);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository repo) {
        return new CreateUserUseCase(repo);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepository repo) {
        return new DeleteUserUseCase(repo);
    }
}
