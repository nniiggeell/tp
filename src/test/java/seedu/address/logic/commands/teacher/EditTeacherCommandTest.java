package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTeacherAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEACHER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.logic.commands.student.ClearStudentCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.testutil.EditTeacherDescriptorBuilder;
import seedu.address.testutil.TeacherBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditTeacherCommand}.
 */
public class EditTeacherCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // check if all fields can be edited
        Teacher editedTeacher = new TeacherBuilder().build();
        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder(editedTeacher).build();
        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(INDEX_FIRST_TEACHER, descriptor);

        String expectedMessage = String.format(EditTeacherCommand.MESSAGE_EDIT_TEACHER_SUCCESS, editedTeacher);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        // this should be INDEX_FIRST_TEACHER - 1
        expectedModel.setTeacher(model.getFilteredTeacherList().get(0), editedTeacher);

        assertCommandSuccess(editTeacherCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTeacher = Index.fromOneBased(7);
        Teacher lastTeacher = model.getFilteredTeacherList().get(indexLastTeacher.getZeroBased());

        TeacherBuilder teacherInList = new TeacherBuilder(lastTeacher);
        Teacher editedTeacher = teacherInList.withName(VALID_NAME_DEE).withPhone(VALID_PHONE_DEE)
                .withTags(VALID_TAG_MONITOR).build();

        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withName(VALID_NAME_DEE)
                .withPhone(VALID_PHONE_DEE).withTags(VALID_TAG_MONITOR).build();
        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(indexLastTeacher, descriptor);

        String expectedMessage = String.format(EditTeacherCommand.MESSAGE_EDIT_TEACHER_SUCCESS, editedTeacher);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTeacher(lastTeacher, editedTeacher);

        assertCommandSuccess(editTeacherCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(INDEX_FIRST_TEACHER,
                new EditTeacherDescriptor());
        Teacher editedTeacher = model.getFilteredTeacherList().get(INDEX_FIRST_TEACHER.getZeroBased());

        String expectedMessage = String.format(EditTeacherCommand.MESSAGE_EDIT_TEACHER_SUCCESS, editedTeacher);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editTeacherCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);

        Teacher teacherInFilteredList = model.getFilteredTeacherList().get(0);
        Teacher editedTeacher = new TeacherBuilder((Teacher) teacherInFilteredList).withName(VALID_NAME_DEE).build();
        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(INDEX_FIRST_STUDENT,
                new EditTeacherDescriptorBuilder().withName(VALID_NAME_DEE).build());

        String expectedMessage = String.format(EditTeacherCommand.MESSAGE_EDIT_TEACHER_SUCCESS, editedTeacher);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTeacher(model.getFilteredTeacherList().get(0), editedTeacher);

        assertCommandSuccess(editTeacherCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTeacherUnfilteredList_failure() {
        Teacher firstTeacher = model.getFilteredTeacherList().get(INDEX_FIRST_TEACHER.getZeroBased());
        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder(firstTeacher).build();
        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(INDEX_SECOND_TEACHER, descriptor);

        assertCommandFailure(editTeacherCommand, model, EditTeacherCommand.MESSAGE_DUPLICATE_TEACHER);
    }

    @Test
    public void execute_duplicateTeacherFilteredList_failure() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);

        // edit teacher in filtered list into a duplicate in address book
        Teacher teacherInList = model.getAddressBook()
                .getTeacherList().get(INDEX_SECOND_TEACHER.getZeroBased());
        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(INDEX_FIRST_TEACHER,
                new EditTeacherDescriptorBuilder(teacherInList).build());

        assertCommandFailure(editTeacherCommand, model, EditTeacherCommand.MESSAGE_DUPLICATE_TEACHER);
    }

    @Test
    public void execute_invalidTeacherIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withName(VALID_NAME_DEE).build();
        EditTeacherCommand editCommand = new EditTeacherCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TEACHER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTeacherIndexFilteredList_failure() {
        showTeacherAtIndex(model, INDEX_FIRST_TEACHER);
        Index outOfBoundIndex = INDEX_SECOND_TEACHER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTeacherList().size());

        EditTeacherCommand editTeacherCommand = new EditTeacherCommand(outOfBoundIndex,
                new EditTeacherDescriptorBuilder().withName(VALID_NAME_DEE).build());

        assertCommandFailure(editTeacherCommand, model, Messages.MESSAGE_INVALID_TEACHER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTeacherCommand standardCommand = new EditTeacherCommand(INDEX_FIRST_TEACHER, DESC_DEE);

        // same values -> returns true
        EditTeacherDescriptor copyDescriptor = new EditTeacherDescriptor(DESC_DEE);
        EditTeacherCommand commandWithSameValues = new EditTeacherCommand(INDEX_FIRST_TEACHER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearStudentCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTeacherCommand(INDEX_SECOND_TEACHER, DESC_DEE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTeacherCommand(INDEX_FIRST_TEACHER, DESC_CHO)));
    }
}
