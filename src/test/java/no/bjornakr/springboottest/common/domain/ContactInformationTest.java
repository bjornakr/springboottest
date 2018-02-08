package no.bjornakr.springboottest.common.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTest {
    private static final String VALID_EMAIL = "jtester@testmail.com";
    private static final String VALID_TELEPHONE_NUMBER = "47 12 34 56 78";

    private ContactInformation.Builder b =
            new ContactInformation.Builder(PostalAddressTest.ValidPostalAdress);

    public static final ContactInformation ValidContactInformation =
            new ContactInformation.Builder(PostalAddressTest.ValidPostalAdress)
                    .eMail(VALID_EMAIL)
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


    @Test(expected = IllegalArgumentException.class)
    public void builder_InvalidEmail_Error() {
        b.eMail("invalid e-mail").build();
    }

    @Test
    public void builder_fieldsWithSpaces_allFieldsTrimmed() {
        String spaces = "   %s   ";
        ContactInformation contactInformation = b
                .eMail(String.format(spaces, VALID_EMAIL))
                .telephoneNumber(String.format(spaces, VALID_TELEPHONE_NUMBER))
                .build();

        assertThat(contactInformation.getEmail(), equalTo(VALID_EMAIL));
        assertThat(contactInformation.getTelephoneNumber(), equalTo(VALID_TELEPHONE_NUMBER));
    }
}
