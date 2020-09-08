package com.example.user;

import java.util.Objects;

public class User {

    private final String firstName;
    private final String lastName;
    private String eMail;
    private boolean emailVerified = false;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return 1;
    }

    public boolean hasNoEmail() {
        return Objects.isNull(eMail);
    }

    public boolean isEmailVerified() {
        if (hasNoEmail()) {
            return false;
        }
        else return emailVerified;
    }

    public void sendEmail(String message) throws UserEmailNotVerifiedException {
        if (!isEmailVerified()) {
            throw new UserEmailNotVerifiedException("E-mail is not verified");
        }
    }
}
