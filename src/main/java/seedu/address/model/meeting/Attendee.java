package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's attendee in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendee(String)}
 */
public class Attendee {
    public static final String MESSAGE_CONSTRAINTS =
            "Attendee must be one of the following in uppercase or lowercase: "
                + "'T'(teachers), 'S'(students), 'P'(parents)";

    public final String value;

    /**
     * Constructs a {@code Attendee}.
     *
     * @param attendee A valid attendee.
     */
    public Attendee(String attendee) {
        requireNonNull(attendee);
        checkArgument(isValidAttendee(attendee), MESSAGE_CONSTRAINTS);
        value = attendee;
    }

    /**
     * Returns true if a given string is a valid attendee.
     */
    public static boolean isValidAttendee(String test) {
        String upperTest = test.toUpperCase();
        return upperTest.matches("T") || upperTest.matches("S") || upperTest.matches("P");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Attendee // instanceof handles nulls
            && value.equals(((Attendee) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
