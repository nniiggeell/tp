package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label dateTime;
    @FXML
    private Label venue;
    @FXML
    private Label attendee;

    /**
     * Creates a {@code MeetingCode} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        title.setText(this.meeting.getTitle().value);
        dateTime.setText("Date: " + meeting.getDisplayDateTime());
        venue.setText("Venue: " + meeting.getVenue().value);
        setAttendee(meeting.getAttendee().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
            && meeting.equals(card.meeting);
    }

    private void setAttendee(String text) {
        switch (text) {
        case "T":
            attendee.setText("Teachers");
            attendee.getStyleClass().add("a-blue");
            break;
        case "S":
            attendee.setText("Students");
            attendee.getStyleClass().add("a-green");
            break;
        case "P":
            attendee.setText("Parents");
            attendee.getStyleClass().add("a-red");
            break;
        default:
            // should never reach this part
            attendee.setText("Invalid");
        }
    }
}
