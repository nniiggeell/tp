package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.student.Student;

/**
 * Controller for a Medical page
 * The Icon for the MedicalWindow is made by Pixel Buddha from www.flaticon.com"
 */
public class MedicalWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(MedicalWindow.class);
    private static final String FXML = "MedicalWindow.fxml";
    private Student student;

    @FXML
    private Label name;
    @FXML
    private Label medicalHistory;

    /**
     * Creates a new MedicalWindow.
     *
     * @param root Stage to use as the root of the MedicalWindow.
     */
    public MedicalWindow(Stage root) {
        super(FXML, root);
        if (student != null) {
            setContent(student);
        }
        medicalHistory.setWrapText(true);
        medicalHistory.setPrefWidth(320);

    }

    /**
     * Creates a new MedicalWindow.
     */
    public MedicalWindow() {
        this(new Stage());
    }

    /**
     * Shows the medical window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing medical history of the student.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public void setContent(Student student) {
        name.setText(student.getName().fullName);
        medicalHistory.setText(student.getMedicalHistory().value);
    }
}
