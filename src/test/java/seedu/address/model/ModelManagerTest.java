package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TEACHER;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.logic.commands.student.DeleteStudentCommand;
import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.logic.commands.teacher.AddTeacherCommand;
import seedu.address.logic.commands.teacher.DeleteTeacherCommand;
import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void hasTeacher_nullTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTeacher(null));
    }

    @Test
    public void hasTeacher_teacherNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTeacher(ALI));
    }

    @Test
    public void hasTeacher_teacherInAddressBook_returnsTrue() {
        modelManager.addTeacher(ALI);
        assertTrue(modelManager.hasTeacher(ALI));
    }

    @Test
    public void getFilteredTeacherList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTeacherList().remove(0));
    }

    // Check that histories are equal, actual testing of the UNDO command is in the UndoCommandTest section.

    @Test
    public void historiesAreEqual_addingStudent_success() {
        ModelManager modelManager = new ModelManagerBuilder().with(new AddStudentCommand(ALICE)).build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.ADD_ALICE));
    }

    @Test
    public void historiesAreEqual_addingTeacher_success() {
        ModelManager modelManager = new ModelManagerBuilder().with(new AddTeacherCommand(ALI)).build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.ADD_ALI));
    }

    @Test
    public void historiesAreEqual_addingMultipleStudents_success() {
        ModelManager modelManager =
                new ModelManagerBuilder().with(new AddStudentCommand(ALICE)).with(new AddStudentCommand(BOB)).build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.ADD_MULTIPLE_STUDENT));
    }

    @Test
    public void historiesAreEqual_addingMultipleTeachers_success() {
        ModelManager modelManager =
                new ModelManagerBuilder().with(new AddTeacherCommand(ALI)).with(new AddTeacherCommand(BEN)).build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.ADD_MULTIPLE_TEACHER));
    }

    @Test
    public void historiesAreEqual_deletingStudent_success() {
        ModelManager modelManager =
                new ModelManagerBuilder()
                        .with(new AddStudentCommand(ALICE))
                        .with(new AddStudentCommand(BOB))
                        .with(new DeleteStudentCommand(INDEX_THIRD_STUDENT))
                        .build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.ADD_MULTIPLE_STUDENT));
    }

    @Test
    public void historiesAreEqual_deletingTeacher_success() {
        ModelManager modelManager =
                new ModelManagerBuilder()
                        .with(new AddTeacherCommand(ALI))
                        .with(new AddTeacherCommand(BEN))
                        .with(new DeleteTeacherCommand(INDEX_THIRD_TEACHER))
                        .build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.ADD_MULTIPLE_TEACHER));
    }

    @Test
    public void historiesAreEqual_filteringStudent_success() {
        // filtering student should not add on to the history
        StudentInvolvementContainsKeywordsPredicate firstPredicate =
                new StudentInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));
        ModelManager modelManager =
                new ModelManagerBuilder()
                        .with(new AddStudentCommand(ALICE))
                        .with(new AddStudentCommand(BOB))
                        .with(new DeleteStudentCommand(INDEX_FIRST_STUDENT))
                        .with(new FilterStudentCommand(firstPredicate))
                        .build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.DELETING_ALICE));
        assertTrue(modelManager.hasEqualHistory(TypicalModels.FILTER_STUDENT_BY_TAG));
    }

    @Test
    public void historiesAreEqual_filteringTeacher_success() {
        // filtering teacher should not add on to the history
        TeacherInvolvementContainsKeywordsPredicate firstPredicate =
                new TeacherInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));
        ModelManager modelManager =
                new ModelManagerBuilder()
                        .with(new AddTeacherCommand(ALI))
                        .with(new AddTeacherCommand(BEN))
                        .with(new DeleteTeacherCommand(INDEX_FIRST_TEACHER))
                        .with(new FilterTeacherCommand(firstPredicate))
                        .build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.DELETING_ALI));
        assertTrue(modelManager.hasEqualHistory(TypicalModels.FILTER_TEACHER_BY_TAG));
    }

    @Test
    public void historiesAreEqual_commandException_success() {
        // if the command execution runs into a CommandException, the history of the ModelManager should not be updated
        StudentInvolvementContainsKeywordsPredicate firstPredicate =
                new StudentInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));
        ModelManager modelManager =
                new ModelManagerBuilder()
                        .with(new AddStudentCommand(ALICE))
                        .with(new AddStudentCommand(BOB))
                        .with(new DeleteStudentCommand(INDEX_FIRST_STUDENT))
                        .with(new FilterStudentCommand(firstPredicate))
                        .build();
        assertTrue(modelManager.hasEqualHistory(TypicalModels.DELETING_ALICE));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withStudent(ALICE).withStudent(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));


        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new StudentNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));


        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTeacherList(Model.PREDICATE_SHOW_ALL_TEACHERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
