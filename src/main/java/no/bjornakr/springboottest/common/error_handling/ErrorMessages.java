package no.bjornakr.springboottest.common.error_handling;

public enum ErrorMessages {
    FirstNameCannotBeNull("First name cannot be null."),
    LastNameCannotBeNull("Last name cannot be null."),
    FirstNameCannotBeEmpty("First name cannot be empty."),
    LastNameCannotBeEmpty("Last name cannot be empty."),
    EmailCannotBeEmpty("E-mail cannot be empty."),
    InvalidEmail("Invalid e-mail: "),
    InvalidPostalCode("Invalid postal code: "),
    PostalAddressCannotBeNull("Postal address cannot be null.")
    ;

    private String value;

    private ErrorMessages(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
