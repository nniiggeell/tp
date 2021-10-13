package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Student;
import seedu.address.model.person.Teacher;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TEACHER = "Teachers list contains duplicate teacher(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTeacher> teachers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students and teachers.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("teachers") List<JsonAdaptedTeacher> teachers) {
        this.students.addAll(students);
        this.teachers.addAll(teachers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getPersonList().stream()
                .filter(person -> person instanceof Student)
                .map(person -> new JsonAdaptedStudent((Student) person)).collect(Collectors.toList()));

        teachers.addAll(source.getPersonList().stream()
                .filter(person -> person instanceof Teacher)
                .map(person -> new JsonAdaptedTeacher((Teacher) person)).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addPerson(student);
        }
        for (JsonAdaptedTeacher jsonAdaptedTeacher : teachers) {
            Teacher teacher = jsonAdaptedTeacher.toModelType();
            if (addressBook.hasPerson(teacher)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEACHER);
            }
            addressBook.addPerson(teacher);
        }
        return addressBook;
    }
}
