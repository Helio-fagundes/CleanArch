package io.meuprojeto.clean_arch_demo.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "John Doe", "teste@gmail.com");
    }

    @Nested
    @DisplayName("name validation Tests")
    class nameIsValid {

        @Test
        void givenValidName_shouldNotThrowException() {
            assertDoesNotThrow(() -> user.nameIsValid("John Doe"));
        }

        @Test
        void givenNullOrBlankName_shouldThrowIllegalArgumentException() {
            assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> user.nameIsValid(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> user.nameIsValid("")),
                () -> assertThrows(IllegalArgumentException.class, () -> user.nameIsValid("   "))
            );
        }
    }

    @Nested
    @DisplayName("email validation Tests")
    class emailIsValid {

        @Test
        void givenValidEmail_shouldNotThrowException() {
            assertDoesNotThrow(() -> user.emailIsValid("teste@gmail.com"));
        }

        @Test
        void givenNullOrBlankEmail_shouldThrowIllegalArgumentException() {
            assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> user.emailIsValid(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> user.emailIsValid("")),
                () -> assertThrows(IllegalArgumentException.class, () -> user.emailIsValid("invalid"))
            );
        }
    }
}