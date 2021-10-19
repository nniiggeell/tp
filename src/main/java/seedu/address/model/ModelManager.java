package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Teacher> filteredTeachers;
    private final Stack<AddressBook> history = new Stack<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredTeachers = new FilteredList<>(this.addressBook.getTeacherList());
        history.push(new AddressBook(this.addressBook));
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        // fitting to our name, we need to create a new AddressBook here because else, the stack will store by
        // reference, which is not what we want
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    // Students
    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return addressBook.hasStudent(student);
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public void deleteStudent(Student target) {
        addressBook.removeStudent(target);
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        addressBook.setStudent(target, editedStudent);
        this.history.push(new AddressBook(this.addressBook));
    }

    // Teacher
    @Override
    public boolean hasTeacher(Teacher teacher) {
        requireNonNull(teacher);
        return addressBook.hasTeacher(teacher);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        addressBook.addTeacher(teacher);
        updateFilteredTeacherList(PREDICATE_SHOW_ALL_TEACHERS);
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public void deleteTeacher(Teacher target) {
        addressBook.removeTeacher(target);
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public void setTeacher(Teacher target, Teacher editedTeacher) {
        requireAllNonNull(target, editedTeacher);
        addressBook.setTeacher(target, editedTeacher);
        this.history.push(new AddressBook(this.addressBook));
    }

    // Meeting
    @Override
    public boolean hasMeetingConflict(Meeting meeting) {
        requireNonNull(meeting);
        return addressBook.hasConflict(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        addressBook.addMeeting(meeting);
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public void deleteMeeting(Meeting target) {
        addressBook.removeMeeting(target);
        this.history.push(new AddressBook(this.addressBook));
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return addressBook.getMeetingList();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Teacher> getFilteredTeacherList() {
        return filteredTeachers;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTeacherList(Predicate<Teacher> predicate) {
        requireNonNull(predicate);
        filteredTeachers.setPredicate(predicate);
    }

    //=========== History =============================================================

    /**
     * Undos the last operation
     *
     * @return True if undo was a success, false otherwise
     */
    public boolean undo() {
        if (history.size() <= 1) {
            return false;
        }
        history.pop();
        this.addressBook.resetData(history.lastElement());
        return true;
    }

    @Override
    public boolean hasEqualHistory(Model obj) {
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        ModelManager other = (ModelManager) obj;
        return history.equals(other.history);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredTeachers.equals(other.filteredTeachers);
    }

    @Override
    public String toString() {
        return this.addressBook.toString();
    }

}
