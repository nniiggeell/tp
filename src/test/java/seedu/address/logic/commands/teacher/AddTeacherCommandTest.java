package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.testutil.TeacherBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddTeacherCommand}.
 */

public class AddTeacherCommandTest {

    @Test
    public void constructor_nullTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTeacherCommand(null));
    }

    @Test
    public void execute_teacherAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTeacherAdded modelStub = new ModelStubAcceptingTeacherAdded();
        Teacher validTeacher = new TeacherBuilder().build();

        CommandResult commandResult = new AddTeacherCommand(validTeacher).execute(modelStub);

        assertEquals(String.format(AddTeacherCommand.MESSAGE_SUCCESS, validTeacher), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTeacher), modelStub.teachersAdded);
    }

    @Test
    public void execute_duplicateTeacher_throwsCommandException() {
        Teacher validTeacher = new TeacherBuilder().build();
        AddTeacherCommand addTeacherCommand = new AddTeacherCommand(validTeacher);
        ModelStub modelStub = new ModelStubWithTeacher(validTeacher);

        assertThrows(CommandException.class,
            AddTeacherCommand.MESSAGE_DUPLICATE_TEACHER, () -> addTeacherCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Teacher cho = new TeacherBuilder().withName("Cho").build();
        Teacher dee = new TeacherBuilder().withName("Dee").build();
        AddTeacherCommand addChoCommand = new AddTeacherCommand(cho);
        AddTeacherCommand addDeeCommand = new AddTeacherCommand(dee);

        // same object -> returns true
        assertTrue(addChoCommand.equals(addChoCommand));

        // same values -> returns true
        AddTeacherCommand addChoCommandCopy = new AddTeacherCommand(cho);
        assertTrue(addChoCommand.equals(addChoCommandCopy));

        // different types -> returns false
        assertFalse(addChoCommand.equals(1));

        // null -> returns false
        assertFalse(addChoCommand.equals(null));

        // different teacher -> returns false
        assertFalse(addChoCommand.equals(addDeeCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTeacher(Teacher teacher) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTeacher(Teacher teacher) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeacher(Teacher target, Teacher editedTeacher) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTeacher(Teacher target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Teacher> getFilteredTeacherList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTeacherList(Predicate<Teacher> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeetingConflict(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEqualHistory(Model other) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single teacher.
     */
    private class ModelStubWithTeacher extends ModelStub {
        private final Teacher teacher;

        ModelStubWithTeacher(Teacher teacher) {
            requireNonNull(teacher);
            this.teacher = teacher;
        }

        @Override
        public boolean hasTeacher(Teacher teacher) {
            requireNonNull(teacher);
            return this.teacher.isSameTeacher(teacher);
        }
    }

    /**
     * A Model stub that always accept the teacher being added.
     */
    private class ModelStubAcceptingTeacherAdded extends ModelStub {
        final ArrayList<Teacher> teachersAdded = new ArrayList<>();

        @Override
        public boolean hasTeacher(Teacher teacher) {
            requireNonNull(teacher);
            return teachersAdded.stream().anyMatch(teacher::isSameTeacher);
        }

        @Override
        public void addTeacher(Teacher teacher) {
            requireNonNull(teacher);
            teachersAdded.add(teacher);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
