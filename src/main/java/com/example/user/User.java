package com.example.user;

import java.util.Objects;
import java.util.Random;

public class User {

    private final String firstName;
    private final String lastName;
    private final int id;
    private String eMail;
    private boolean emailVerified = false;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = new Random().nextInt(2);
    }

    public User(int id, String firstName, String lastName) {
        this.id = id;
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
        return id;
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
