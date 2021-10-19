package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's medical history in the address book.
 * Guarantees: immutable; is always valid
 */
public class MedicalHistory {
    public final String value;

    /**
     * Constructs a {@code MedicalHistory}.
     *
     * @param medicalHistory A medicalHistory.
     */
    public MedicalHistory(String medicalHistory) {
        requireNonNull(medicalHistory);
        value = medicalHistory;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalHistory // instanceof handles nulls
                && value.equals(((MedicalHistory) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
