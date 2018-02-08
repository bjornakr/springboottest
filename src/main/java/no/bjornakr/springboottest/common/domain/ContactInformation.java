package no.bjornakr.springboottest.common.domain;

import no.bjornakr.springboottest.common.error_handling.ErrorMessages;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class ContactInformation {
    private String eMail;
    private String telephoneNumber;

    @Embedded
    private PostalAddress postalAddress;


    protected ContactInformation() {
    }

    private ContactInformation(Builder builder) {
        this.eMail = builder.eMail;
        this.telephoneNumber = builder.telephoneNumber;
        this.postalAddress = builder.postalAddress;
    }

    public String getEmail() {
        return eMail;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public static class Builder {
        private String eMail;
        private String telephoneNumber;
        private PostalAddress postalAddress;

        public Builder(PostalAddress postalAddress, String eMail) {
            Objects.requireNonNull(postalAddress, ErrorMessages.PostalAddressCannotBeNull.toString());
            Objects.requireNonNull(eMail, String.format("%s'%s'.", ErrorMessages.EmailCannotBeEmpty, eMail));
            String eMailTrimmed = eMail.trim();

            if (eMailTrimmed.isEmpty()) {
                throw new IllegalArgumentException(ErrorMessages.EmailCannotBeEmpty.toString());
            }
            if (!isEmailValid(eMailTrimmed)) {
                String errorMessage = String.format("%s'%s'.", ErrorMessages.InvalidEmail, eMailTrimmed);
                throw new IllegalArgumentException(errorMessage);
            }

            this.postalAddress = postalAddress;
            this.eMail = eMailTrimmed;
        }


        public ContactInformation build() {
            return new ContactInformation(this);
        }

        public Builder telephoneNumber(String value) {
            telephoneNumber = value.trim();
            return this;
        }

        // Taken from StackOverflow (Jason Buberel): https://stackoverflow.com/a/8204716
        private static boolean isEmailValid(String emailStr) {
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                            Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
        }

    }
}
