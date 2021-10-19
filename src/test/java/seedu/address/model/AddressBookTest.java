package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeacherBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void studentConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void teacherConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getTeacherList());
    }

    @Test
    public void meetingConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getMeetingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        //same name and address = same person
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_MONITOR)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasTeacher_nullTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTeacher(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasTeacher_teacherNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTeacher(ALI));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasTeacher_teacherInAddressBook_returnsTrue() {
        addressBook.addTeacher(ALI);
        assertTrue(addressBook.hasTeacher(ALI));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        //same name and address = same student
        addressBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_MONITOR)
                .build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void hasTeacher_teacherWithSameIdentityFieldsInAddressBook_returnsTrue() {
        //same name and office table number = same teacher
        addressBook.addTeacher(ALI);
        Teacher editedAli = new TeacherBuilder(ALI).withTags(VALID_TAG_MONITOR)
            .build();
        assertTrue(addressBook.hasTeacher(editedAli));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void getTeacherList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTeacherList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose students and teachers list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Teacher> getTeacherList() {
            return teachers;
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
    }

}
