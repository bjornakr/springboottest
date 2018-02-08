package no.bjornakr.springboottest.common.domain;

import no.bjornakr.springboottest.common.error_handling.EntityConstructionException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class PostalAddressTest {
    private final static String TEST_STREET1 = "Street 1";
    private final static String TEST_STREET2 = "Street 2";
    private final static String TEST_POSTAL_CODE = "1234";
    private final static String TEST_CITY = "Testico City";
    private final static String TEST_COUNTRY = "Testlandia";

    public final static PostalAddress ValidPostalAdress = new PostalAddress(
            TEST_STREET1,
            TEST_STREET2,
            TEST_POSTAL_CODE,
            TEST_CITY,
            TEST_COUNTRY
    );

    @Test
    public void jpaConstructor_Success() {
        new PostalAddress();
    }

    @Test
    public void constructor_validFields_success() {
        assertThat(ValidPostalAdress, is(notNullValue()));
    }

    @Test
    public void constructor_emptyOptionalStrings_convertedToNull() {
        PostalAddress testAddress = new PostalAddress(
                "   ", "   ", "  ", "  ", "  "
        );

        assertThat(testAddress.getStreet1(), is(nullValue()));
        assertThat(testAddress.getStreet2(), is(nullValue()));
        assertThat(testAddress.getPostalCode(), is(nullValue()));
        assertThat(testAddress.getCity(), is(nullValue()));
        assertThat(testAddress.getCountry(), is(nullValue()));
    }

    @Test
    public void constructor_validFields_trimsAllFields() {
        PostalAddress testAddress = new PostalAddress(
                String.format("  %s  ", TEST_STREET1),
                String.format("  %s  ", TEST_STREET2),
                String.format("  %s  ", TEST_POSTAL_CODE),
                String.format("  %s  ", TEST_CITY),
                String.format("  %s  ", TEST_COUNTRY)
        );

        assertThat(testAddress.getStreet1(), equalTo(TEST_STREET1));
        assertThat(testAddress.getStreet2(), equalTo(TEST_STREET2));
        assertThat(testAddress.getPostalCode(), equalTo(TEST_POSTAL_CODE));
        assertThat(testAddress.getCity(), equalTo(TEST_CITY));
        assertThat(testAddress.getCountry(), equalTo(TEST_COUNTRY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_invalidPostalNumber_error() {
        new PostalAddress(
                TEST_STREET1,
                TEST_STREET2,
                "NOT A POSTAL CODE",
                TEST_CITY,
                TEST_COUNTRY
        );
    }
}