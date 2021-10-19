package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TEACHERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CAR;
import static seedu.address.testutil.TypicalPersons.DAN;
import static seedu.address.testutil.TypicalPersons.EL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.TeacherNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code FindTeacherCommand}.
 */

public class FindTeacherCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TeacherNameContainsKeywordsPredicate firstPredicate =
                new TeacherNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TeacherNameContainsKeywordsPredicate secondPredicate =
                new TeacherNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTeacherCommand findFirstCommand = new FindTeacherCommand(firstPredicate);
        FindTeacherCommand findSecondCommand = new FindTeacherCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTeacherCommand findFirstCommandCopy = new FindTeacherCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different teacher -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTeacherFound() {
        String expectedMessage = String.format(MESSAGE_TEACHERS_LISTED_OVERVIEW, 0);
        TeacherNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTeacherCommand command = new FindTeacherCommand(predicate);
        expectedModel.updateFilteredTeacherList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTeacherList());
    }

    @Test
    public void execute_multipleKeywords_multipleTeachersFound() {
        String expectedMessage = String.format(MESSAGE_TEACHERS_LISTED_OVERVIEW, 3);
        TeacherNameContainsKeywordsPredicate predicate = preparePredicate("Car Dan El");
        FindTeacherCommand command = new FindTeacherCommand(predicate);
        expectedModel.updateFilteredTeacherList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CAR, DAN, EL), model.getFilteredTeacherList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TeacherNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TeacherNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
