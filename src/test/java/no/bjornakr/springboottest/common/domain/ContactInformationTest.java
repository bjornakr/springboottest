package no.bjornakr.springboottest.common.domain;

import no.bjornakr.springboottest.CommonTexts;
import no.bjornakr.springboottest.common.error_handling.ErrorMessages;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class ContactInformationTest {
    private static final String VALID_EMAIL = "jtester@testmail.com";
    private static final String VALID_TELEPHONE_NUMBER = "47 12 34 56 78";

    private ContactInformation.Builder b =
            new ContactInformation.Builder(
                    PostalAddressTest.ValidPostalAdress,
                    VALID_EMAIL
            );

    public static final ContactInformation ValidContactInformation =
            new ContactInformation.Builder(PostalAddressTest.ValidPostalAdress, VALID_EMAIL)
                    .telephoneNumber(VALID_TELEPHONE_NUMBER)
                    .build();


    @Test
    public void jpaConstructor_invoked_Success() {
        assertThat(new ContactInformation(), is(notNullValue()));
    }

    @Test
    public void builder_ValidFields_Success() {
        assertThat(ValidContactInformation, is(notNullValue()));
        assertThat(ValidContactInformation.getTelephoneNumber(),
                is(equalTo(VALID_TELEPHONE_NUMBER)));
        assertThat(ValidContactInformation.getEmail(),
                is(equalTo(VALID_EMAIL)));
        assertThat(ValidContactInformation.getPostalAddress(),
                is(equalTo(PostalAddressTest.ValidPostalAdress)));
    }


    @Test
    public void builder_ContactInformationIsNull_Error() {
        try {
            new ContactInformation.Builder(null, VALID_EMAIL);
            fail(CommonTexts.EXCEPTION_SHOULD_HAVE_BEEN_THROWN);
        }
        catch (NullPointerException e) {
            assertThat(e.getMessage(),
                    containsString(ErrorMessages.PostalAddressCannotBeNull.toString()));
        }
    }

    @Test
    public void builder_EmailIsNull_Error() {
        try {
            new ContactInformation.Builder(
                    PostalAddressTest.ValidPostalAdress, null);
            fail(CommonTexts.EXCEPTION_SHOULD_HAVE_BEEN_THROWN);
        }
        catch (NullPointerException e) {
            assertThat(e.getMessage(),
                    containsString(ErrorMessages.EmailCannotBeEmpty.toString()));
        }
    }

    @Test
    public void builder_EmailIsEmpty_Error() {
        try {
            new ContactInformation.Builder(
                    PostalAddressTest.ValidPostalAdress, "   ");
            fail(CommonTexts.EXCEPTION_SHOULD_HAVE_BEEN_THROWN);
        }
        catch (IllegalArgumentException e) {
            assertThat(e.getMessage(),
                    containsString(ErrorMessages.EmailCannotBeEmpty.toString()));
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void builder_InvalidEmail_Error() {
        new ContactInformation.Builder(
                PostalAddressTest.ValidPostalAdress,
                "invalid e-mail")
                .build();
    }

    @Test
    public void builder_fieldsWithSpaces_allFieldsTrimmed() {
        String spaces = "   %s   ";
        ContactInformation contactInformation = b
                .telephoneNumber(String.format(spaces, VALID_TELEPHONE_NUMBER))
                .build();

        assertThat(contactInformation.getEmail(), equalTo(VALID_EMAIL));
        assertThat(contactInformation.getTelephoneNumber(), equalTo(VALID_TELEPHONE_NUMBER));
    }
}
