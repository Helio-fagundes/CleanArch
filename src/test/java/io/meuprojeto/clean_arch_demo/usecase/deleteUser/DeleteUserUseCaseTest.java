package io.meuprojeto.clean_arch_demo.usecase.deleteUser;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.NotExistUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;

    @Nested
    @DisplayName("Tests for execute method")
    class Execute {


        @Test
        void givenValidUserId_whenExecute_shouldNotThrow() {

            Long userId = 1L;

            when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "John Doe", "teste@gmail.com")));
            var response = deleteUserUseCase.execute(userId);

            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals("User with ID (" + userId + ") has been deleted.", response)
            );
        }

        @Test
        void givenNonExistentUserId_whenExecute_shouldThrowNotExistUserException() {

            Long userId = 1L;

            when(userRepository.findById(userId)).thenReturn(Optional.empty());

            var exception = assertThrows(NotExistUserException.class, () -> deleteUserUseCase.execute(userId));

            assertEquals("User with ID (" + userId + ") does not exist.", exception.getMessage());
        }

        @Test
        void givenNullUserId_whenExecute_shouldThrowIllegalArgumentException() {

            var exception = assertThrows(IllegalArgumentException.class, () -> deleteUserUseCase.execute(null));

            assertEquals("ID cannot be null.", exception.getMessage());
        }

        @Test
        void givenErrorInRepository_whenExecute_shouldPropagateException() {

            Long userId = 1L;

            when(userRepository.findById(userId)).thenThrow(new RuntimeException("Database error"));

            var exception = assertThrows(RuntimeException.class, () -> deleteUserUseCase.execute(userId));

            assertEquals("Database error", exception.getMessage());
        }
    }

}