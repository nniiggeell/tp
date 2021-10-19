package seedu.address.logic.commands.teacher;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTeacherAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ListTeacherCommand}.
 */

public class ListTeacherCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTeacherCommand(), model, ListTeacherCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);
        assertCommandSuccess(new ListTeacherCommand(), model, ListTeacherCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
