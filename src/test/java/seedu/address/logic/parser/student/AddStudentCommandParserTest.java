package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMERGENCY_CONTACT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMERGENCY_CONTACT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FORM_CLASS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FORM_CLASS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMERGENCY_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORM_CLASS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INVOLVEMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVOLVEMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVOLVEMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FORM_CLASS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Address;
import seedu.address.model.person.student.FormClass;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedPerson = new StudentBuilder(BOB).withTags(VALID_TAG_REP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                new AddStudentCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                new AddStudentCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                new AddStudentCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                new AddStudentCommand(expectedPerson));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_AMY + GENDER_DESC_BOB,
            new AddStudentCommand(expectedPerson));

        // multiple emergency contact numbers - last emergency contact number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP + EMERGENCY_CONTACT_DESC_AMY
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
            new AddStudentCommand(expectedPerson));

        // multiple form classes - last form class accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP + EMERGENCY_CONTACT_DESC_BOB
                + FORM_CLASS_DESC_AMY + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
            new AddStudentCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                new AddStudentCommand(expectedPerson));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_REP, VALID_TAG_MONITOR)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVOLVEMENT_DESC_BOB + TAG_DESC_MONITOR + TAG_DESC_REP + EMERGENCY_CONTACT_DESC_BOB
                        + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                new AddStudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + INVOLVEMENT_DESC_AMY + EMERGENCY_CONTACT_DESC_AMY + FORM_CLASS_DESC_AMY + GENDER_DESC_AMY,
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                    + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                    + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + GENDER_DESC_BOB
                    + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB,
                expectedMessage);

        // missing gender prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_GENDER_BOB
                + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB,
            expectedMessage);

        // missing involvement prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + VALID_INVOLVEMENT_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                    + INVOLVEMENT_DESC_BOB + VALID_ADDRESS_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB,
                expectedMessage);

        // missing emergency contact prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                    + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + VALID_EMERGENCY_CONTACT_BOB + FORM_CLASS_DESC_BOB,
                expectedMessage);

        // missing form class prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + VALID_FORM_CLASS_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_GENDER_BOB
                    + VALID_INVOLVEMENT_BOB + VALID_ADDRESS_BOB + VALID_EMERGENCY_CONTACT_BOB + VALID_FORM_CLASS_BOB ,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB
                + TAG_DESC_MONITOR + TAG_DESC_REP, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB
                + TAG_DESC_MONITOR + TAG_DESC_REP, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + GENDER_DESC_BOB
                + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB
                + TAG_DESC_MONITOR + TAG_DESC_REP, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_GENDER_DESC
                + INVOLVEMENT_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB
                + TAG_DESC_MONITOR + TAG_DESC_REP, Gender.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INVOLVEMENT_DESC_BOB + TAG_DESC_MONITOR + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid involvement
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_INVOLVEMENT_DESC + TAG_DESC_MONITOR + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB, Involvement.MESSAGE_CONSTRAINTS);

        // invalid form class
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB + TAG_DESC_MONITOR + TAG_DESC_REP
                + EMERGENCY_CONTACT_DESC_BOB + INVALID_FORM_CLASS_DESC + GENDER_DESC_BOB,
                FormClass.MESSAGE_CONSTRAINTS);

        // invalid emergency contact
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB + TAG_DESC_MONITOR + TAG_DESC_REP
                + INVALID_EMERGENCY_CONTACT_DESC + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + INVOLVEMENT_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB
                + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_MONITOR
                + TAG_DESC_REP + EMERGENCY_CONTACT_DESC_BOB + FORM_CLASS_DESC_BOB + GENDER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
