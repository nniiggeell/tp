package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.NonConflictMeetingList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.person.teacher.UniqueTeacherList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final UniqueTeacherList teachers;
    private final NonConflictMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        teachers = new UniqueTeacherList();
        meetings = new NonConflictMeetingList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the teacher list with {@code teachers}.
     * {@code teachers} must not contain duplicate teachers.
     */
    public void setTeachers(List<Teacher> teachers) {
        this.teachers.setTeachers(teachers);
    }

    /**
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain conflicting meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTeachers(newData.getTeacherList());
        setMeetings(newData.getMeetingList());
    }

    //// student-level operation

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same
     * as another existing student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);

        return students.contains(student);
    }

    //// teacher-level operation

    /**
     * Adds a teacher to the address book.
     * The teacher must not already exist in the address book.
     */
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTeacher(Teacher key) {
        teachers.remove(key);
    }

    /**
     * Replaces the given teacher {@code target} in the list with {@code editedTeacher}.
     * {@code target} must exist in the address book.
     * The Teacher identity of {@code editedTeacher} must not be the same
     * as another existing Teacher in the address book.
     */
    public void setTeacher(Teacher target, Teacher editedTeacher) {
        requireNonNull(editedTeacher);

        teachers.setTeacher(target, editedTeacher);
    }

    /**
     * Returns true if a teacher with the same identity as {@code teacher} exists in the address book.
     */
    public boolean hasTeacher(Teacher teacher) {
        requireNonNull(teacher);

        return teachers.contains(teacher);
    }

    //// meeting-level operations

    /**
     * Adds a meeting to the address book.
     * The meeting must not clash with another existing meeting.
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    /**
     * Returns true if a meeting that clashes with {@code meeting} exists in the address book.
     *
     */
    public boolean hasConflict(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.hasConflictWith(meeting);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students "
                + teachers.asUnmodifiableObservableList().size() + " teachers "
                + meetings.asUnmodifiableObservableList().size() + " meetings.";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Teacher> getTeacherList() {
        return teachers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        } else {
            if (other instanceof AddressBook) {
                // handles nulls
                AddressBook otherAddressBook = (AddressBook) other;
                return students.equals(otherAddressBook.students)
                        && teachers.equals(otherAddressBook.teachers);
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, teachers);
    }
}
