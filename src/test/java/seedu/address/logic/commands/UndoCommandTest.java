package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.TypicalModels.FIRST_STUDENT_PREDICATE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARP_NOT_IN_LIST;
import static seedu.address.testutil.TypicalPersons.EDITED_FISH_NOT_IN_LIST;
import static seedu.address.testutil.TypicalPersons.EXTRA_TEACHER_NOT_IN_LIST;
import static seedu.address.testutil.TypicalPersons.FISH_NOT_IN_LIST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private UndoCommand undoCommand = new UndoCommand();


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void testEmptyHistory_failToUndo() {
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_ALREADY_AT_OLDEST_CHANGE, expectedModel);
    }

    @Test
    public void nonEmptyHistory_oneAddition_success() {
        // add in a student and undoing it should give back the same model
        model.addStudent(FISH_NOT_IN_LIST);
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS,
                expectedModel);
        assertTrue(model.hasEqualHistory(expectedModel));
    }

    @Test
    public void nonEmptyHistory_twoAdditions_success() {
        // add in a student and undoing it should give back the same model
        model.addStudent(FISH_NOT_IN_LIST);
        model.addStudent(CARP_NOT_IN_LIST);
        undoCommand.execute(model);
        assertNotEquals(model, expectedModel);
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS,
                expectedModel);
        assertTrue(model.hasEqualHistory(expectedModel));
    }

    @Test
    public void undoDelete_success() {
        // add in a student and undoing it should give back the same model
        model.addStudent(FISH_NOT_IN_LIST);
        model.deleteStudent(FISH_NOT_IN_LIST);
        expectedModel.addStudent(FISH_NOT_IN_LIST);
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS,
                expectedModel);
        assertTrue(model.hasEqualHistory(expectedModel));
    }

    @Test
    public void undoDelete_multiple_success() {
        model.addStudent(FISH_NOT_IN_LIST);
        model.deleteStudent(FISH_NOT_IN_LIST);
        model.deleteStudent(ALICE);
        model.addStudent(ALICE);
        undoCommand.execute(model);
        undoCommand.execute(model);
        undoCommand.execute(model);
        // after 4 undoes, you should get back the same model
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS,
                expectedModel);
        // and they have equal histories
        assertTrue(model.hasEqualHistory(expectedModel));
    }

    @Test
    public void undoEdit_success() {
        model.addStudent(FISH_NOT_IN_LIST);
        expectedModel.addStudent(FISH_NOT_IN_LIST);
        model.setStudent(FISH_NOT_IN_LIST, EDITED_FISH_NOT_IN_LIST);
        // You should get back the same model
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS,
                expectedModel);
        // and they have equal histories
        assertTrue(model.hasEqualHistory(expectedModel));
    }

    /**
     * Test if undo can handle both student and teachers operations
     */
    @Test
    public void undoOperations_bothStudentsAndTeachers_success() {
        model.addStudent(FISH_NOT_IN_LIST);
        model.addTeacher(EXTRA_TEACHER_NOT_IN_LIST);
        model.setStudent(FISH_NOT_IN_LIST, EDITED_FISH_NOT_IN_LIST);
        model.deleteTeacher(EXTRA_TEACHER_NOT_IN_LIST);
        undoCommand.execute(model);
        undoCommand.execute(model);
        undoCommand.execute(model);
        // after 4 undoes, you should get back the same model
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS,
                expectedModel);
        // and they have equal histories
        assertTrue(model.hasEqualHistory(expectedModel));
    }

    @Test
    public void unequalHistory() {
        model.addStudent(FISH_NOT_IN_LIST);
        model.deleteStudent(FISH_NOT_IN_LIST);
        assertFalse(model.hasEqualHistory(expectedModel));
    }

    @Test
    public void filter_doesNotAffectHistory() {
        FilterStudentCommand filterStudentCommand = new FilterStudentCommand(FIRST_STUDENT_PREDICATE);
        filterStudentCommand.execute(model);
        assertTrue(model.hasEqualHistory(expectedModel));
        filterStudentCommand.execute(expectedModel);
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_ALREADY_AT_OLDEST_CHANGE, expectedModel);
    }
}
