package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.MedicalHistory;
import seedu.address.model.person.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MedicalHistoryCommand}.
 */

class MedicalHistoryCommandTest {
    private static final String MEDICAL_HISTORY_STUB = "Some medical history";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMedicalHistoryUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentBuilder studentInList = new StudentBuilder(firstStudent);
        Student editedStudent = studentInList.withMedicalHistory(MEDICAL_HISTORY_STUB).build();
        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(editedStudent.getMedicalHistory().value));

        String expectedMessage = String.format(MedicalHistoryCommand.MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(medicalHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMedicalHistoryUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withMedicalHistory("").build();

        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(editedStudent.getMedicalHistory().toString()));

        String expectedMessage = String.format(MedicalHistoryCommand.MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(medicalHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT
                .getZeroBased())).withMedicalHistory(MEDICAL_HISTORY_STUB).build();

        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(editedStudent.getMedicalHistory().value));

        String expectedMessage = String.format(MedicalHistoryCommand.MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(medicalHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(outOfBoundIndex,
                new MedicalHistory(VALID_MEDICAL_HISTORY_BOB));

        assertCommandFailure(medicalHistoryCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(outOfBoundIndex,
                new MedicalHistory(VALID_MEDICAL_HISTORY_BOB));

        assertCommandFailure(medicalHistoryCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MedicalHistoryCommand standardCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_AMY));

        // same values -> returns true
        MedicalHistoryCommand commandWithSameValues = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearStudentCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MedicalHistoryCommand(INDEX_SECOND_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_AMY))));

        // different MedicalHistory -> returns false
        assertFalse(standardCommand.equals(new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_BOB))));
    }
}
