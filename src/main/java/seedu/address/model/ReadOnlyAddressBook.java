package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the teacher list.
     * This list will not contain any duplicate teachers.
     */
    ObservableList<Teacher> getTeacherList();

    /**
     * Returns an unmodifiable view of the meeting list.
     * This list will not contain any conflicting meetings.
     */
    ObservableList<Meeting> getMeetingList();

}
