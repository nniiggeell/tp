package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendeeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendee(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalid = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendee(invalid));
    }

    @Test
    public void isValidAttendee() {
        // null
        assertThrows(NullPointerException.class, () -> Attendee.isValidAttendee(null));

        // invalid
        assertFalse(Attendee.isValidAttendee("")); // empty string
        assertFalse(Attendee.isValidAttendee(" ")); // spaces only
        assertFalse(Attendee.isValidAttendee("-T*")); //correct within wrong
        assertFalse(Attendee.isValidAttendee("-")); // wrong character

        // valid
        assertTrue(Attendee.isValidAttendee("T"));
        assertTrue(Attendee.isValidAttendee("P"));
        assertTrue(Attendee.isValidAttendee("S"));
    }
}
