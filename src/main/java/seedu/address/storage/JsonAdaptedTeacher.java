package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Teacher}.
 */
class JsonAdaptedTeacher extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Teacher's %s field is missing!";

    private final String officeTable;

    /**
     * Constructs a {@code JsonAdaptedTeacher} with the given teacher details.
     */
    @JsonCreator
    public JsonAdaptedTeacher(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("gender") String gender,
                              @JsonProperty("involvement") String involvement,
                              @JsonProperty("officeTable") String officeTable,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, phone, email, gender, involvement, tagged);
        this.officeTable = officeTable;
    }

    /**
     * Converts a given {@code Teacher} into this class for Jackson use.
     */
    public JsonAdaptedTeacher(Teacher source) {
        super(source);
        this.officeTable = source.getOfficeTable().value;
    }

    /**
     * Converts this Jackson-friendly adapted teacher object into the model's {@code Teacher} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted teacher.
     */
    @Override
    public Teacher toModelType() throws IllegalValueException {
        final List<Tag> teacherTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            teacherTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (gender == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (involvement == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Involvement.class.getSimpleName()));
        }
        if (!Involvement.isValidInvolvement(involvement)) {
            throw new IllegalValueException(Involvement.MESSAGE_CONSTRAINTS);
        }
        final Involvement modelInvolvement = new Involvement(involvement);

        if (officeTable == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, OfficeTable.class.getSimpleName()));
        }
        if (!OfficeTable.isValidTable(officeTable)) {
            throw new IllegalValueException(OfficeTable.MESSAGE_CONSTRAINTS);
        }
        final OfficeTable modelOfficeTable = new OfficeTable(officeTable);

        final Set<Tag> modelTags = new HashSet<>(teacherTags);
        return new Teacher(modelName, modelPhone, modelEmail, modelGender, modelInvolvement,
                modelOfficeTable, modelTags);
    }
}
