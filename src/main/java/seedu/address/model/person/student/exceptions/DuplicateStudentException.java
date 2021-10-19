package seedu.address.model.person.student.exceptions;

import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Signals that the operation will result in duplicate Students
 * (Students are considered duplicates if they have the same identity).
 */
public class DuplicateStudentException extends DuplicatePersonException {
    public DuplicateStudentException() {
        super("students");
    }
}
