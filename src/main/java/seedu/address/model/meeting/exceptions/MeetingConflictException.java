package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in meeting conflicts.
 */
public class MeetingConflictException extends RuntimeException {
    public MeetingConflictException() {
        super("Operation would result in meeting conflicts.");
    }
}

