package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStudent(Student student) {
        addressBook.addStudent(student);
        return this;
    }

    /**
     * Adds a new {@code Teacher} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTeacher(Teacher teacher) {
        addressBook.addTeacher(teacher);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
