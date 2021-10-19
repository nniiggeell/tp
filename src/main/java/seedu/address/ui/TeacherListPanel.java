package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.teacher.Teacher;

/**
 * Panel containing the list of teachers.
 */
public class TeacherListPanel extends UiPart<Region> {
    private static final String FXML = "TeacherListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TeacherListPanel.class);

    @FXML
    private ListView<Teacher> teacherListView;

    /**
     * Creates a {@code TeacherListPanel} with the given {@code ObservableList}.
     */
    public TeacherListPanel(ObservableList<Teacher> teacherList) {
        super(FXML);
        teacherListView.setItems(teacherList);
        teacherListView.setCellFactory(listView -> new TeacherListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Teacher} using a {@code TeacherCard}.
     */
    class TeacherListViewCell extends ListCell<Teacher> {
        @Override
        protected void updateItem(Teacher teacher, boolean empty) {
            super.updateItem(teacher, empty);

            if (empty || teacher == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TeacherCard(teacher, getIndex() + 1).getRoot());
            }
        }
    }

}
