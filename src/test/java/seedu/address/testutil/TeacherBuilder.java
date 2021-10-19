package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Teacher objects.
 */
public class TeacherBuilder {

    public static final String DEFAULT_NAME = "Dee ku";
    public static final String DEFAULT_PHONE = "44444444";
    public static final String DEFAULT_EMAIL = "dee@example.com";
    public static final String DEFAULT_GENDER = "N";
    public static final String DEFAULT_INVOLVEMENT = "4A Co-Form";
    public static final String DEFAULT_OFFICE_TABLE = "12";

    private Name name;
    private Phone phone;
    private Email email;
    private Gender gender;
    private Involvement involvement;
    private OfficeTable officeTable;
    private Set<Tag> tags;

    /**
     * Creates a {@code TeacherBuilder} with the default details.
     */
    public TeacherBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gender = new Gender(DEFAULT_GENDER);
        involvement = new Involvement(DEFAULT_INVOLVEMENT);
        officeTable = new OfficeTable(DEFAULT_OFFICE_TABLE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TeacherBuilder with the data of {@code teacherToCopy}.
     */
    public TeacherBuilder(Teacher teacherToCopy) {
        name = teacherToCopy.getName();
        phone = teacherToCopy.getPhone();
        email = teacherToCopy.getEmail();
        gender = teacherToCopy.getGender();
        involvement = teacherToCopy.getInvolvement();
        officeTable = teacherToCopy.getOfficeTable();
        tags = new HashSet<>(teacherToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Involvement} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withInvolvement(String involvement) {
        this.involvement = new Involvement(involvement);
        return this;
    }

    /**
     * Sets the {@code OfficeTable} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withOfficeTable(String officeTable) {
        this.officeTable = new OfficeTable(officeTable);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Teacher} that we are building.
     */
    public TeacherBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Teacher build() {
        return new Teacher(name, phone, email, gender, involvement, officeTable, tags);
    }

}
