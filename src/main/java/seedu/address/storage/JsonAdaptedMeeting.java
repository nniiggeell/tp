package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.MeetingExpiredException;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    protected final String dateTime;
    protected final String title;
    protected final String venue;
    protected final String attendee;


    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("dateTime") String dateTime, @JsonProperty("title") String title,
                             @JsonProperty("venue") String venue, @JsonProperty("attendee") String attendee) {
        this.dateTime = dateTime;
        this.title = title;
        this.venue = venue;
        this.attendee = attendee;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        dateTime = source.getDateTime().value;
        title = source.getTitle().value;
        venue = source.getVenue().value;
        attendee = source.getAttendee().value;
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException, MeetingExpiredException {
        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DateTime.class.getSimpleName()));
        }
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        if (DateTime.isPastDateTime(dateTime)) {
            throw new MeetingExpiredException();
        }
        final DateTime modelDateTime = new DateTime(dateTime);

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Title"));
        }
        if (!Description.isValidDescription(title)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelTitle = new Description(title);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Venue"));
        }
        if (!Description.isValidDescription(venue)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelVenue = new Description(venue);

        if (attendee == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Attendee.class.getSimpleName()));
        }
        if (!Attendee.isValidAttendee(attendee)) {
            throw new IllegalValueException(Attendee.MESSAGE_CONSTRAINTS);
        }
        final Attendee modelAttendee = new Attendee(attendee);
        return new Meeting(modelDateTime, modelAttendee, modelTitle, modelVenue);
    }
}
