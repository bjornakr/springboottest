package no.bjornakr.desertsnake.common.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class RespondentTest {
    private final static Respondent ValidRespondent = new Respondent(
            NameTest.ValidName,
            ContactInformationTest.ValidContactInformation
    );

    @Test
    public void jpaConstructor_Invoked_Success() {
        new Respondent();
    }

    @Test
    public void constructor_ValidFields_Success() {
        assertThat(ValidRespondent, is(notNullValue()));
        assertThat(ValidRespondent.getId(), is(nullValue()));
        assertThat(ValidRespondent.getContactInformation(),
                is(equalTo(ContactInformationTest.ValidContactInformation)));
        assertThat(ValidRespondent.getName(),
                is(equalTo(NameTest.ValidName)));
    }

    @Test(expected = NullPointerException.class)
    public void constructor_NameIsNull_Error() {
        new Respondent(null, ContactInformationTest.ValidContactInformation);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_ConstactInformationIsNull_Error() {
        new Respondent(NameTest.ValidName, null);
    }




//    @Test
//    public void constructor_fieldsWithSpaces_trimsAllFields() {
//        Respondent respondent = new Respondent(
//                new Name(String.format("  %s  ", FIRST_NAME),
//                String.format("  %s  ", LAST_NAME)),
//                String.format("  %s  ", EMAIL),
//                String.format("  %s  ", TELEPHONE_NUMBER),
//                null
//        );
//
//        assertThat(respondent.getFirstName(), equalTo(FIRST_NAME));
//        assertThat(respondent.getLastName(), equalTo(LAST_NAME));
//        assertThat(respondent.getEmail(), equalTo(EMAIL));
//        assertThat(respondent.getTelephoneNumber(), equalTo(TELEPHONE_NUMBER));
//    }
}
