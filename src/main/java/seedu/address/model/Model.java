package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Teacher> PREDICATE_SHOW_ALL_TEACHERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //Students
    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same
     * as another existing student in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    //Teachers
    /**
     * Returns true if a teacher with the same identity as {@code teacher} exists in the address book.
     */
    boolean hasTeacher(Teacher teacher);

    /**
     * Adds the given teacher.
     * {@code teacher} must not already exist in the address book.
     */
    void addTeacher(Teacher teacher);

    /**
     * Deletes the given teacher.
     * The teacher must exist in the address book.
     */
    void deleteTeacher(Teacher target);

    /**
     * Replaces the given teacher {@code target} with {@code editedTeacher}.
     * {@code target} must exist in the address book.
     * The teacher identity of {@code editedTeacher} must not be the same
     * as another existing student in the address book.
     */
    void setTeacher(Teacher target, Teacher editedTeacher);

    /** Returns an unmodifiable view of the filtered teacher list */
    ObservableList<Teacher> getFilteredTeacherList();

    /**
     * Updates the filter of the filtered teacher list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTeacherList(Predicate<Teacher> predicate);

    //Meetings
    /** Returns an unmodifiable view of the meeting list */
    ObservableList<Meeting> getMeetingList();

    /**
     * Returns true if a meeting that clashes with {@code meeting} exists in the address book.
     */
    boolean hasMeetingConflict(Meeting meeting);

    /**
     * Adds the given meeting.
     * {@code meeting} must not clash with another meeting in the address book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Deletes the given meeting.
     * The meeting must exist in the address book.
     */
    void deleteMeeting(Meeting target);

    /**
     * Undos the previous action.
     * @return True if undo is success, false otherwise
     */
    boolean undo();

    /**
     * Undos the previous action.
     * @return true if other model has equal history, false otherwise
     */
    boolean hasEqualHistory(Model other);

}
