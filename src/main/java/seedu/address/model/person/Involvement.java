package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's involvement in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInvolvement(String)}
 */
public class Involvement {

    public static final String MESSAGE_CONSTRAINTS =
            "Involvement can take any values, and it should not be blank";

    /*
     * The first character of the involvement must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Involvement}.
     *
     * @param i A valid involvement.
     */
    public Involvement(String i) {
        requireNonNull(i);
        checkArgument(isValidInvolvement(i), MESSAGE_CONSTRAINTS);
        value = i;
    }

    /**
     * Returns true if a given string is a valid involvement.
     */
    public static boolean isValidInvolvement(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Involvement // instanceof handles nulls
                && value.equals(((Involvement) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
