package com.example.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Test first name and last name")
    public void testNewUser() {
        var user = new User("John", "Smith");
        assertEquals("John", user.getFirstName(),
                "User should return first name");
        assertNotNull(user.getLastName());
    }

    @Test
    @DisplayName("\uD83D\uDD25")
    public void testGetId() {
        var user = new User("No", "Name");
        assertNotNull(user.getId());
    }

    @Test
    public void testIsEmailVerified() {
        var user = new User("No", "Name");
        assertAll("E-mail assertions",
                () -> assertTrue(user.hasNoEmail(),
                        "User has no email by default"),
                () -> assertFalse(user.isEmailVerified(),
                        "User's e-mail is not verified by default"));
    }

    @Test
    @DisplayName("Check whenever same ID is assigned to two different users")
    @Disabled("Not required until ID generator service is ready")
    public void testIdIsNotTheSame() {
        var user = new User("", "");
        var anotherUser = new User("", "");
        assertNotEquals(user.getId(), anotherUser.getId(),
                "ID should not be re-assigned");
    }

    @Test
    public void testSendEmail_whenEmailIsNotVerified() {
        var user = new User("No", "Name");
        var exception = assertThrows(
                UserEmailNotVerifiedException.class,
                () -> user.sendEmail("Hey there!"),
                "We should not send messages to unverified e-mails");
        assertEquals("E-mail is not verified", exception.getMessage());
    }

}
