package io.meuprojeto.clean_arch_demo.usecase.createUser;

import io.meuprojeto.clean_arch_demo.domain.model.User;
import io.meuprojeto.clean_arch_demo.domain.repository.UserRepository;
import io.meuprojeto.clean_arch_demo.usecase.dto.UserRequestDto;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.EmailAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Nested;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private UserRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new UserRequestDto("John Doe", "teste@gmail.com");
    }

    @Nested
    @DisplayName("Tests for execute method")
    class Execute{

        @Test
        void givenValidUserRequestDto_whenExecute_shouldNotThrow() {

            User savedUser = new User(1L, requestDto.getNome(), requestDto.getEmail());

            when(userRepository.save(any(User.class))).thenReturn(savedUser);
            var response = createUserUseCase.execute(requestDto);

            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(savedUser.getId(), response.id()),
                    () -> assertEquals(savedUser.getNome(), response.nome()),
                    () -> assertEquals(savedUser.getEmail(), response.email())
            );
        }

        @Test
        void givenUserRequestDtoWithNullNameOrEmail_shouldThrowIllegalArgumentException() {

            UserRequestDto dtoNullName = new UserRequestDto(null, "teste@gmail.com");
            UserRequestDto dtoNullEmail = new UserRequestDto("John Doe", null);
            UserRequestDto dtoEmpty = new UserRequestDto("", "");
            UserRequestDto dtoNullBoth = new UserRequestDto(null, null);

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(dtoNullName)),
                    () -> assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(dtoNullEmail)),
                    () -> assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(dtoEmpty)),
                    () -> assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(dtoNullBoth))
            );
        }

        @Test
        void givenUserRequestDtoWithInvalidEmail_shouldThrowIllegalArgumentException() {

            UserRequestDto dtoInvalidEmail = new UserRequestDto("John Doe", "invalidemail.com");

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> createUserUseCase.execute(dtoInvalidEmail))
            );
        }

        @Test
        void givenUserRequestDtoWithEmailExistingInDatabase_whenExecute_shouldThrowIllegalArgumentException() {

            User existingUser = new User(1L, "Existing User", requestDto.getEmail());

            when(userRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.of(existingUser));

            assertAll(
                    () -> assertThrows(EmailAlreadyExistsException.class, () -> createUserUseCase.execute(requestDto))
            );
        }
    }
}