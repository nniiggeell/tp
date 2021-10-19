package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterStudentCommand}.
 */
public class FilterStudentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentInvolvementContainsKeywordsPredicate firstPredicate =
                new StudentInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));
        StudentInvolvementContainsKeywordsPredicate secondPredicate =
                new StudentInvolvementContainsKeywordsPredicate(Collections.singletonList("second"));

        FilterStudentCommand findFirstCommand = new FilterStudentCommand(firstPredicate);
        FilterStudentCommand findSecondCommand = new FilterStudentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterStudentCommand findFirstCommandCopy = new FilterStudentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        StudentInvolvementContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterStudentCommand command = new FilterStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound2() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        StudentInvolvementContainsKeywordsPredicate predicate = preparePredicate("class t/owesmoney");
        FilterStudentCommand command = new FilterStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private StudentInvolvementContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StudentInvolvementContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
