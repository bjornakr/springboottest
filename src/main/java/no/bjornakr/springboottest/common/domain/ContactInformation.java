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

        public Builder(PostalAddress value) {
            postalAddress = value;
        }

        public ContactInformation build() {
            return new ContactInformation(this);
        }

        public Builder eMail(String value) {
            Objects.requireNonNull(value);
            String trimmedEmail = value.trim();
            if (!isEmailValid(trimmedEmail)) {
                String errorMessage = String.format("%s'%s'.", ErrorMessages.InvalidEmail, trimmedEmail);
                throw new IllegalArgumentException(errorMessage);
            }
            eMail = trimmedEmail;
            return this;
        }

        public Builder telephoneNumber(String value) {
            telephoneNumber = value.trim();
            return this;
        }

//        public Builder postalAddress(PostalAddress value) {
//            postalAddress = value;
//            return this;
//        }

        // Taken from StackOverflow (Jason Buberel): https://stackoverflow.com/a/8204716
        private boolean isEmailValid(String emailStr) {
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                            Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
        }

    }


//    public ContactInformation(
//            String eMail,
//            String telephoneNumber,
//            PostalAddress postalAddress) {
//        Objects.requireNonNull(postalAddress);
//
//
//        String trimmedTelephoneNo = telephoneNumber.trim();
//
//        this.eMail = eMail;
//        this.telephoneNumber = telephoneNumber;
//        this.postalAddress = postalAddress;
//    }
//
//    // Taken from StackOverflow (Jason Buberel): https://stackoverflow.com/a/8204716
//    private boolean isEmailValid(String emailStr) {
//        Pattern VALID_EMAIL_ADDRESS_REGEX =
//                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
//                        Pattern.CASE_INSENSITIVE);
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
//        return matcher.find();
//    }
//
//    private String validateEmail(String eMail) {
//        String s = Trimmer.apply(eMail);
//        if (!isEmailValid(s)) {
//            String err = String.format("%s'%s'.", ErrorMessages.InvalidEmail, s);
//            throw new EntityConstructionException(this.getClass(), err);
//        }
//        return s;
//    }
}
