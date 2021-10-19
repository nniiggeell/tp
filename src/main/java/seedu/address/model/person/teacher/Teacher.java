package seedu.address.model.person.teacher;

import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Teacher in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Teacher extends Person {

    private final OfficeTable officeTable;

    /**
     * Constructor for {@code Teacher}
     * @param name Name of the teacher
     * @param phone Phone number of the teacher
     * @param email Email of the teacher
     * @param gender Gender of the teacher
     * @param involvement Involvement of the teacher
     * @param officeTable Office table number of the teacher
     * @param tags Tags associated to the teacher
     */
    public Teacher(Name name, Phone phone, Email email, Gender gender, Involvement involvement,
                   OfficeTable officeTable, Set<Tag> tags) {
        super(name, phone, email, gender, involvement, tags);
        this.officeTable = officeTable;
    }

    /**
     * Constructor for {@code Teacher} from person
     *
     * @param person person to copy the data from
     * @param officeTable office table number of the teacher.
     */
    public Teacher(Person person, OfficeTable officeTable) {
        super(person.getName(), person.getPhone(), person.getEmail(), person.getGender(), person.getInvolvement(),
                person.getTags());
        this.officeTable = officeTable;
    }

    public OfficeTable getOfficeTable() {
        return this.officeTable;
    }

    /**
     * Returns true if both teacher have the same name and office table
     * This defines a weaker notion of equality between two teacher
     */
    public boolean isSameTeacher(Teacher otherTeacher) {
        if (otherTeacher == this) {
            return true;
        }

        return otherTeacher != null
                && otherTeacher.getName().equals(getName())
                && otherTeacher.getOfficeTable().equals(getOfficeTable());
    }

    /**
     * Returns true if both person are teachers, and have the same identity and data fields.
     * This defines a stronger notion of equality between two teachers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Teacher)) {
            return false;
        }

        Teacher otherTeacher = (Teacher) other;
        return super.equals(otherTeacher)
                && this.getOfficeTable().equals(otherTeacher.getOfficeTable());
    }

    @Override
    public String toString() {
        return super.toString() + "; " + String.format("Office table number: %s", getOfficeTable());
    }
}
