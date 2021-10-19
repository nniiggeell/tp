package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.teacher.Teacher;

/**
 * An UI component that displays information of a {@code Teacher}.
 */
public class TeacherCard extends UiPart<Region> {

    private static final String FXML = "TeacherListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Teacher teacher;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label gender;
    @FXML
    private Label involvement;
    @FXML
    private Label officeTable;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TeacherCode} with the given {@code Teacher} and index to display.
     */
    public TeacherCard(Teacher teacher, int displayedIndex) {
        super(FXML);
        this.teacher = teacher;
        id.setText(displayedIndex + ". ");
        name.setText(teacher.getName().fullName);
        phone.setText(teacher.getPhone().value);
        email.setText(teacher.getEmail().value);
        gender.setText(teacher.getGender().value);
        officeTable.setText(teacher.getOfficeTable().value);
        involvement.setText(teacher.getInvolvement().value);
        teacher.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeacherCard)) {
            return false;
        }

        // state check
        TeacherCard card = (TeacherCard) other;
        return id.getText().equals(card.id.getText())
                && teacher.equals(card.teacher);
    }
}
