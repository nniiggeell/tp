package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TEACHERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code FilterTeacherCommand}.
 */

public class FilterTeacherCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TeacherInvolvementContainsKeywordsPredicate firstPredicate =
                new TeacherInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));
        TeacherInvolvementContainsKeywordsPredicate secondPredicate =
                new TeacherInvolvementContainsKeywordsPredicate(Collections.singletonList("second"));

        FilterTeacherCommand findFirstCommand = new FilterTeacherCommand(firstPredicate);
        FilterTeacherCommand findSecondCommand = new FilterTeacherCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterTeacherCommand findFirstCommandCopy = new FilterTeacherCommand(firstPredicate);
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
        TeacherInvolvementContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterTeacherCommand command = new FilterTeacherCommand(predicate);
        expectedModel.updateFilteredTeacherList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTeacherList());
    }

    @Test
    public void execute_multipleKeywords_multipleTeachersFound2() { //TODO: probably need to remove the 2 at the back
        // TODO: probably need to do filtering in a different way
        String expectedMessage = String.format(MESSAGE_TEACHERS_LISTED_OVERVIEW, 2);
        TeacherInvolvementContainsKeywordsPredicate predicate = preparePredicate("math t/friends");
        FilterTeacherCommand command = new FilterTeacherCommand(predicate);
        expectedModel.updateFilteredTeacherList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALI, BEN), model.getFilteredTeacherList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TeacherInvolvementContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TeacherInvolvementContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
