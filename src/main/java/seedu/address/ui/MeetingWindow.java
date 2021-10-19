package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

/**
 * Window to show upcoming meetings.
 */
public class MeetingWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(MeetingWindow.class);
    private static final String FXML = "MeetingWindow.fxml";

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private MeetingListPanel meetingListPanel;

    @FXML
    private StackPane meetingListPanelPlaceholder;

    /**
     * Creates a new HelpWindow.
     */
    public MeetingWindow() {
        this(new Stage());
    }

    /**
     * Creates a new MeetingWindow.
     *
     * @param root Stage to use as the root of the MeetingWindow.
     */
    public MeetingWindow(Stage root) {
        super(FXML, root);
    }

    void fillInnerParts(ObservableList<Meeting> meetingList) {
        System.out.println("=================================================================");
        System.out.println(meetingList.size());
        this.meetingListPanel = new MeetingListPanel(meetingList);
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());
    }

    /**
     * Shows the meeting window.
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
        logger.fine("Showing upcoming meetings.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the meeting window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meeting window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meeting window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    public void setWindowDefaultSize(GuiSettings guiSettings) {
        getRoot().setHeight(guiSettings.getWindowHeight());
        getRoot().setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            getRoot().setX(guiSettings.getWindowCoordinates().getX());
            getRoot().setY(guiSettings.getWindowCoordinates().getY());
        }
    }

}
