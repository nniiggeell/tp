package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BEN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.teacher.OfficeTable;

public class JsonAdaptedTeacherTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_OFFICE_TABLE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_INVOLVEMENT = " ";
    private static final String INVALID_GENDER = "WASD";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BEN.getName().toString();
    private static final String VALID_PHONE = BEN.getPhone().toString();
    private static final String VALID_EMAIL = BEN.getEmail().toString();
    private static final String VALID_INVOLVEMENT = BEN.getInvolvement().toString();
    private static final String VALID_GENDER = BEN.getGender().toString();
    private static final String VALID_OFFICE_TABLE = BEN.getOfficeTable().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BEN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTeacherDetails_returnsTeacher() throws Exception {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(BEN);
        assertEquals(BEN, teacher.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(null, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = String.format(JsonAdaptedTeacher.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, null, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = String.format(JsonAdaptedTeacher.MISSING_FIELD_MESSAGE_FORMAT,
                Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, null, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = String.format(JsonAdaptedTeacher.MISSING_FIELD_MESSAGE_FORMAT,
                Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = String.format(JsonAdaptedTeacher.MISSING_FIELD_MESSAGE_FORMAT,
                Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_invalidInvolvement_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                INVALID_INVOLVEMENT, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = Involvement.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_nullInvolvement_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                null, VALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = String.format(JsonAdaptedTeacher.MISSING_FIELD_MESSAGE_FORMAT,
                Involvement.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_invalidOfficeTable_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, INVALID_OFFICE_TABLE, VALID_TAGS);

        String expectedMessage = OfficeTable.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_nullOfficeTable_throwsIllegalValueException() {
        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, null, VALID_TAGS);

        String expectedMessage = String.format(JsonAdaptedTeacher.MISSING_FIELD_MESSAGE_FORMAT,
                OfficeTable.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, teacher::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));

        JsonAdaptedTeacher teacher = new JsonAdaptedTeacher(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_INVOLVEMENT, VALID_OFFICE_TABLE, invalidTags);
        assertThrows(IllegalValueException.class, teacher::toModelType);
    }


}
