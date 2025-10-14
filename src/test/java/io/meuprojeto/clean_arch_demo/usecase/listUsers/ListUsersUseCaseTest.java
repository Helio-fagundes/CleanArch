package io.meuprojeto.clean_arch_demo.usecase.listUsers;

import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListUsersUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUsersUseCase listUsersUseCase;

    @Nested
    @DisplayName("Tests for execute method")
    class Execute {

        @Test
        void given_whenExecute_shouldCallRepositoryFindAll() {
            listUsersUseCase.execute();
            assertDoesNotThrow(() -> userRepository.findAll());
        }

        @Test
        void given_whenExecute_shouldReturnListOfUserResponseDto() {
            var response = listUsersUseCase.execute();
            assertNotNull(response);
        }

        @Test
        void given_whenExecute_shouldReturnEmptyList() {
            var response = listUsersUseCase.execute();
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertTrue(response.isEmpty())
            );
        }

        @Test
        void givenErrorInRepository_whenExecute_shouldThrowException() {
            assertThrows(Exception.class, () -> {
                throw new Exception("Database error");
            });
        }
    }

}