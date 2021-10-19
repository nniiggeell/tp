package seedu.address.model.person.teacher.exceptions;

import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Signals that the operation will result in duplicate Teachers
 * (Teachers are considered duplicates if they have the same identity).
 */
public class DuplicateTeacherException extends DuplicatePersonException {
    public DuplicateTeacherException() {
        super("teachers");
    }
}
