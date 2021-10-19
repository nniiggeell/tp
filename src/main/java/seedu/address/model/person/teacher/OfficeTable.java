package seedu.address.model.person.teacher;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Teacher's office table number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTable(String)}
 */
public class OfficeTable {
    public static final String MESSAGE_CONSTRAINTS =
            "Office table numbers should only contain numbers, and it should have at least 1 digit";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code OfficeTable}.
     *
     * @param tableNumber A valid table number.
     */
    public OfficeTable(String tableNumber) {
        requireNonNull(tableNumber);
        checkArgument(isValidTable(tableNumber), MESSAGE_CONSTRAINTS);
        value = tableNumber;
    }

    /**
     * Returns true if a given string is a valid table number.
     */
    public static boolean isValidTable(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OfficeTable // instanceof handles nulls
                && value.equals(((OfficeTable) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
