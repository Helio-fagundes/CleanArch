package io.meuprojeto.clean_arch_demo.usecase.searchUser;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.NotExistUserException;
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
class SearchUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SearchUserUseCase searchUserUseCase;

    @Nested
    class Execute {

        @Test
        void givenValidUserId_whenExecute_shouldNotThrow() {

            Long userId = 1L;

            when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "John Doe", "teste@gmail.com")));

            assertAll(
                    () -> assertNotNull(searchUserUseCase.execute(userId))
            );
        }

        @Test
        void givenNullUserId_whenExecute_shouldThrowIllegalArgumentException() {

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> searchUserUseCase.execute(null))
            );
        }

        @Test
        void givenNonExistentUserId_whenExecute_shouldThrowException() {

            Long userId = 999L;

            when(userRepository.findById(999L)).thenReturn(Optional.empty());

            assertAll(
                    () -> assertThrows(NotExistUserException.class, () -> searchUserUseCase.execute(userId))
            );

        }

        @Test
        void givenErrorInRepository_whenExecute_shouldThrowException() {
            Long userId = 1L;

            when(userRepository.findById(userId)).thenThrow(new RuntimeException("Database error"));

            assertAll(
                    () -> assertThrows(RuntimeException.class, () -> searchUserUseCase.execute(userId))
            );
        }
    }
}