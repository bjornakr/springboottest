package no.bjornakr.springboottest.common.domain;

import no.bjornakr.springboottest.common.error_handling.ErrorMessages;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    private String firstName;
    private String lastName;

    protected Name() {
        // Requred by JPA - DO NOT USE!!!
    }

    public Name(String firstName, String lastName) {
        Objects.requireNonNull(firstName, ErrorMessages.FirstNameCannotBeNull.toString());
        Objects.requireNonNull(lastName, ErrorMessages.LastNameCannotBeNull.toString());

        String firstNameTrimmed = firstName.trim();
        String lastNameTrimmed = lastName.trim();

        if (firstNameTrimmed.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.FirstNameCannotBeEmpty.toString());
        }
        if (lastNameTrimmed.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.LastNameCannotBeEmpty.toString());
        }

        this.firstName = firstNameTrimmed;
        this.lastName = lastNameTrimmed;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
