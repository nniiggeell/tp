package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.teacher.OfficeTable;

public class OfficeTableTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OfficeTable(null));
    }

    @Test
    public void constructor_invalidOfficeTable_throwsIllegalArgumentException() {
        String invalidOfficeTable = "";
        assertThrows(IllegalArgumentException.class, () -> new OfficeTable(invalidOfficeTable));
    }

    @Test
    public void isValidOfficeTable() {
        // null office table number
        assertThrows(NullPointerException.class, () -> OfficeTable.isValidTable(null));

        // invalid office table numbers
        assertFalse(OfficeTable.isValidTable("")); // empty string
        assertFalse(OfficeTable.isValidTable(" ")); // spaces only
        assertFalse(OfficeTable.isValidTable("table")); // non-numeric
        assertFalse(OfficeTable.isValidTable("12d")); // alphabets within digits
        assertFalse(OfficeTable.isValidTable("1 2")); // spaces within digits

        // valid office table numbers
        assertTrue(OfficeTable.isValidTable("2")); // short table numbers
        assertTrue(OfficeTable.isValidTable("124293842033123")); // long table numbers
    }
}
