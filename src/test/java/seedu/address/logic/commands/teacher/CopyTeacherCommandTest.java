package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.logic.commands.student.ClearStudentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.person.teacher.TeacherNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code CopyTeacherCommand}.
 */

public class CopyTeacherCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validPhoneCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<Teacher> teacherList = model.getFilteredTeacherList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teacherList.size(); i++) {
            if (i == teacherList.size() - 1) {
                sb.append(teacherList.get(i).getPhone());
            } else {
                sb.append(teacherList.get(i).getPhone() + " ");
            }
        }
        assertEquals(new CopyTeacherCommand(copyCommandDescriptor).getCopyContent(model.getFilteredTeacherList()),
                sb.toString());
    }

    @Test
    public void execute_validEmailCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("email");
        List<Teacher> teacherList = model.getFilteredTeacherList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teacherList.size(); i++) {
            if (i == teacherList.size() - 1) {
                sb.append(teacherList.get(i).getEmail());
            } else {
                sb.append(teacherList.get(i).getEmail() + ",");
            }
        }
        assertEquals(new CopyTeacherCommand(copyCommandDescriptor).getCopyContent(model.getFilteredTeacherList()),
                sb.toString());
    }

    @Test
    public void execute_validNameCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("name");
        List<Teacher> teacherList = model.getFilteredTeacherList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teacherList.size(); i++) {
            if (i == teacherList.size() - 1) {
                sb.append(teacherList.get(i).getName());
            } else {
                sb.append(teacherList.get(i).getName() + ", ");
            }
        }
        assertEquals(new CopyTeacherCommand(copyCommandDescriptor).getCopyContent(model.getFilteredTeacherList()),
                sb.toString());
    }

    @Test
    public void execute_differentCommand_failure() {
        // do a sanity check
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<Teacher> teacherList = model.getFilteredTeacherList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teacherList.size(); i++) {
            if (i == teacherList.size() - 1) {
                sb.append(teacherList.get(i).getEmail());
            } else {
                sb.append(teacherList.get(i).getEmail() + ",");
            }
        }
        assertNotEquals(new CopyTeacherCommand(copyCommandDescriptor).getCopyContent(model.getFilteredTeacherList()),
                sb.toString());
    }

    @Test
    public void execute_invalidCommand_failure() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("fish");
        assertEquals(new CopyTeacherCommand(copyCommandDescriptor).getCopyContent(model.getFilteredTeacherList()), "");
    }

    @Test
    public void execute_fromFilteredList_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<String> keywords = new ArrayList<>();
        keywords.add("Alice");
        TeacherNameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new TeacherNameContainsKeywordsPredicate(keywords);
        model.updateFilteredTeacherList(nameContainsKeywordsPredicate);
        List<Teacher> teacherList = model.getFilteredTeacherList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teacherList.size(); i++) {
            if (i == teacherList.size() - 1) {
                sb.append(teacherList.get(i).getPhone());
            } else {
                sb.append(teacherList.get(i).getPhone() + " ");
            }
        }
        assertEquals(new CopyTeacherCommand(copyCommandDescriptor).getCopyContent(model.getFilteredTeacherList()),
                sb.toString());
    }

    @Test
    public void equals() {
        final CopyTeacherCommand copyEmailCommand = new CopyTeacherCommand(
                new CopyCommandDescriptor("email"));
        final CopyTeacherCommand anotherCopyEmailCommand = new CopyTeacherCommand(
                new CopyCommandDescriptor("email"));
        final CopyTeacherCommand copyPhoneCommand = new CopyTeacherCommand(
                new CopyCommandDescriptor("phone"));
        final CopyTeacherCommand invalidCommand = new CopyTeacherCommand(
                new CopyCommandDescriptor("invalid"));
        final CopyTeacherCommand anotherInvalidCommand = new CopyTeacherCommand(
                new CopyCommandDescriptor("invalid2"));

        // same values -> returns true
        assertTrue(copyEmailCommand.equals(anotherCopyEmailCommand));

        // same object -> returns true
        assertTrue(copyEmailCommand.equals(copyEmailCommand));

        // null -> returns false
        assertFalse(copyEmailCommand.equals(null));

        // different types -> returns false
        assertFalse(copyEmailCommand.equals(new ClearStudentCommand()));

        // different descriptor but command still valid -> returns false
        assertFalse(copyEmailCommand.equals(copyPhoneCommand));

        // different descriptor but command invalid -> returns false
        assertFalse(copyEmailCommand.equals(invalidCommand));

        // different descriptor but command invalid -> returns false
        assertFalse(copyEmailCommand.equals(invalidCommand));

        // all invalid commands are the same -> returns true
        assertTrue(invalidCommand.equals(anotherInvalidCommand));
    }

}
