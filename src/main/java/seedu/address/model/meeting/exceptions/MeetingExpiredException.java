package seedu.address.model.meeting.exceptions;

public class MeetingExpiredException extends Exception {
    public MeetingExpiredException() {
        super("Meeting has expired.");
    }
}
