package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Meeting implements Comparable<Meeting> {

    private final Attendee attendee;
    private final DateTime dateTime;
    private final Description title;
    private final Description venue;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param dateTime datetime of the meeting.
     * @param attendee attendee of the meeting.
     * @param title summary of the meeting.
     * @param venue details of the meeting.
     */
    public Meeting(DateTime dateTime, Attendee attendee, Description title, Description venue) {
        requireAllNonNull(dateTime, attendee, title, venue);

        this.dateTime = dateTime;
        this.attendee = attendee;
        this.title = title;
        this.venue = venue;
    }

    public Description getTitle() {
        return title;
    }

    public Description getVenue() {
        return venue;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public String getDisplayDateTime() {
        return dateTime.getUserFormat();
    }

    /**
     * Returns true if there is a datetime conflict with the other meeting, false otherwise.
     *
     * @param other The other meeting.
     */
    public boolean hasConflictWith(Meeting other) {
        return this.dateTime.equals(other.dateTime);
    }

    @Override
    public String toString() {
        return String.format("Title: %s\nAttendee: %s\nDatetime: %s\nVenue: %s",
                title.toString(), attendee.toString(), dateTime.getUserFormat(), venue.toString());
    }

    /**
     * Returns true if both meetings have the same title, attendee, datetime and venue.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return this.dateTime.equals(otherMeeting.dateTime)
            && this.attendee.equals(otherMeeting.attendee)
            && this.title.equals(otherMeeting.title)
            && this.venue.equals(otherMeeting.venue);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, attendee, dateTime, venue);
    }

    @Override
    public int compareTo(Meeting other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
