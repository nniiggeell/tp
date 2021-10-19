package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "19-08-2022 12:12";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void constructor_pastDateTime_throwsIllegalArgumentException() {
        String pastDateTime = "1998-07-12 12:12";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(pastDateTime));
    }

    @Test
    public void isPastDateTime() {
        // null dateTime
        assertThrows(NullPointerException.class, () -> DateTime.isPastDateTime(null));

        // expired datetime
        assertTrue(DateTime.isPastDateTime("2019-04-12 12:23"));
    }

    @Test
    public void isValidDateTime() {
        // null dateTime
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid dateTime formats
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime("  ")); // spaces only
        assertFalse(DateTime.isValidDateTime("2022-20-08 02:02")); // YYYY-DD-MM HH:mm
        assertFalse(DateTime.isValidDateTime("19-08-2022 02:02")); // DD-MM-YYYY HH:mm
        assertFalse(DateTime.isValidDateTime("08-19-2022 02:02")); // MM-DD-YYYY HH:mm
        assertFalse(DateTime.isValidDateTime("08-2022-19 02:02")); // MM-YYYY-DD HH:mm
        assertFalse(DateTime.isValidDateTime("19-2022-08 02:02")); // DD-YYYY-MM HH:mm
        assertFalse(DateTime.isValidDateTime("19-2022-08 02:02")); // DD-YYYY-MM HH:mm
        assertFalse(DateTime.isValidDateTime("2022-08-20 2:02")); // Missing digit for hour
        assertFalse(DateTime.isValidDateTime("2022-08-20 02:2")); // Missing digit for minute
        assertFalse(DateTime.isValidDateTime("2022-8-20 02:2")); // Missing digit for month
        assertFalse(DateTime.isValidDateTime("2022-08-2 02:2")); // Missing digit for day
        assertFalse(DateTime.isValidDateTime("2022/08/20 02:2")); // Wrong separator used for date
        assertFalse(DateTime.isValidDateTime("2022-08-20 02-12")); // Wrong separator used for time
        assertFalse(DateTime.isValidDateTime("2022-08-20 40:12")); // invalid value of hour
        assertFalse(DateTime.isValidDateTime("2022-08-20 12:80")); // invalid value of minute
        assertFalse(DateTime.isValidDateTime("2022-08-32 12:30")); // invalid day (more than 31)
        assertFalse(DateTime.isValidDateTime("2022-13-21 12:30")); // invalid day (more than 31)

        // valid dateTimes
        assertTrue(DateTime.isValidDateTime("2022-08-20 13:24"));
        assertTrue(DateTime.isValidDateTime("2022-02-31 13:24")); // day does not match but < 32
    }
}
