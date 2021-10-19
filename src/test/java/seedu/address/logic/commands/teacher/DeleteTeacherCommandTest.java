package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTeacherAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEACHER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.Teacher;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteTeacherCommand}.
 */
public class DeleteTeacherCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Teacher teacherToDelete = model.getFilteredTeacherList().get(INDEX_FIRST_TEACHER.getZeroBased());
        DeleteTeacherCommand deleteCommand = new DeleteTeacherCommand(INDEX_FIRST_TEACHER);

        String expectedMessage = String.format(DeleteTeacherCommand.MESSAGE_DELETE_TEACHER_SUCCESS, teacherToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTeacher(teacherToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeacherList().size() + 1);
        DeleteTeacherCommand deleteCommand = new DeleteTeacherCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TEACHER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);

        Teacher teacherToDelete = model.getFilteredTeacherList().get(INDEX_FIRST_TEACHER.getZeroBased());
        DeleteTeacherCommand deleteCommand = new DeleteTeacherCommand(INDEX_FIRST_TEACHER);

        String expectedMessage = String.format(DeleteTeacherCommand.MESSAGE_DELETE_TEACHER_SUCCESS, teacherToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTeacher(teacherToDelete);
        showNoTeacher(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);

        Index outOfBoundIndex = INDEX_SECOND_TEACHER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        DeleteTeacherCommand deleteCommand = new DeleteTeacherCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TEACHER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTeacherCommand deleteFirstCommand = new DeleteTeacherCommand(INDEX_FIRST_TEACHER);
        DeleteTeacherCommand deleteSecondCommand = new DeleteTeacherCommand(INDEX_SECOND_TEACHER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTeacherCommand deleteFirstCommandCopy = new DeleteTeacherCommand(INDEX_FIRST_TEACHER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different teacher -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTeacher(Model model) {
        model.updateFilteredTeacherList(p -> false);

        assertTrue(model.getFilteredTeacherList().isEmpty());
    }
}

