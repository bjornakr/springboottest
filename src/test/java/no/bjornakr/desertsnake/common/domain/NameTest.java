package no.bjornakr.desertsnake.common.domain;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class NameTest {
    private static final String VALID_FIRST_NAME = "John";
    private static final String VALID_LAST_NAME = "Tester";

    public static final Name ValidName = new Name(VALID_FIRST_NAME, VALID_LAST_NAME);

    @Test
    public void jpaConstructor_Invoked_Success() {
        new Name();
    }

    @Test
    public void constructor_ValidFields_Success() {
        new Name(VALID_FIRST_NAME, VALID_LAST_NAME);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_FirstNameIsNull_Error() {
        new Name(null, VALID_LAST_NAME);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_LastNameIsNull_Error() {
        new Name(VALID_FIRST_NAME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_FirstNameIsEmpty_Error() {
        new Name("   ", VALID_LAST_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_LastNameIsEmpty_Error() {
        new Name(VALID_FIRST_NAME, "   ");
    }

    @Test
    public void constructor_FirstNameWithSpaces_FirstNameIsTrimmed() {
        String firstNameWithSpaces = String.format("  %s  ", VALID_FIRST_NAME);
        Name name = new Name(firstNameWithSpaces, VALID_LAST_NAME);
        assertThat(name.getFirstName(), equalTo(VALID_FIRST_NAME));
    }

    @Test
    public void constructor_LastNameWithSpaces_LastNameIsTrimmed() {
        String lastNameWithSpaces = String.format("  %s  ", VALID_LAST_NAME);
        Name name = new Name(VALID_FIRST_NAME, lastNameWithSpaces);
        assertThat(name.getLastName(), equalTo(VALID_LAST_NAME));
    }
}
