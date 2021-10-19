package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INVOLVEMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OFFICE_TABLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVOLVEMENT_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.INVOLVEMENT_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_TABLE_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_TABLE_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.CHO;
import static seedu.address.testutil.TypicalPersons.DEE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.teacher.AddTeacherCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TeacherBuilder;

public class AddTeacherCommandParserTest {
    private AddTeacherCommandParser parser = new AddTeacherCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Teacher expectedTeacher = new TeacherBuilder(DEE).withTags(VALID_TAG_REP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE
                + GENDER_DESC_DEE + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_REP,
            new AddTeacherCommand(expectedTeacher));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_CHO + NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE
                + GENDER_DESC_DEE + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_REP,
            new AddTeacherCommand(expectedTeacher));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_DEE + PHONE_DESC_CHO + PHONE_DESC_DEE + EMAIL_DESC_DEE
                + GENDER_DESC_DEE + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_REP,
            new AddTeacherCommand(expectedTeacher));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_CHO
                + EMAIL_DESC_DEE + GENDER_DESC_DEE + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_REP,
            new AddTeacherCommand(expectedTeacher));

        // multiple genders - last address accepted
        assertParseSuccess(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_CHO
                + GENDER_DESC_DEE + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_REP,
            new AddTeacherCommand(expectedTeacher));

        // multiple tags - all accepted
        Teacher expectedTeacherMultipleTags = new TeacherBuilder(DEE).withTags(VALID_TAG_REP, VALID_TAG_MONITOR)
            .build();
        assertParseSuccess(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_MONITOR + TAG_DESC_REP,
            new AddTeacherCommand(expectedTeacherMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Teacher expectedTeacher = new TeacherBuilder(CHO).withTags().build();
        assertParseSuccess(parser,
            NAME_DESC_CHO + PHONE_DESC_CHO + EMAIL_DESC_CHO + GENDER_DESC_CHO
                + INVOLVEMENT_DESC_CHO + OFFICE_TABLE_DESC_CHO, new AddTeacherCommand(expectedTeacher));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeacherCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
            VALID_NAME_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
            NAME_DESC_DEE + VALID_PHONE_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
            NAME_DESC_DEE + PHONE_DESC_DEE + VALID_EMAIL_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE,
            expectedMessage);

        // missing gender prefix
        assertParseFailure(parser,
            NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + VALID_GENDER_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE,
            expectedMessage);

        // missing involvement prefix
        assertParseFailure(parser,
            NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + VALID_INVOLVEMENT_DEE + OFFICE_TABLE_DESC_DEE,
            expectedMessage);

        // missing office table prefix
        assertParseFailure(parser,
            NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + VALID_OFFICE_TABLE_DEE,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_NAME_DEE + VALID_PHONE_DEE + VALID_EMAIL_DEE + VALID_GENDER_DEE
                + VALID_INVOLVEMENT_DEE + VALID_OFFICE_TABLE_DEE,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE
                + TAG_DESC_MONITOR + TAG_DESC_REP, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_DEE + INVALID_PHONE_DESC + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE
                + TAG_DESC_MONITOR + TAG_DESC_REP, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_DEE + PHONE_DESC_DEE + INVALID_EMAIL_DESC + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE
                + TAG_DESC_MONITOR + TAG_DESC_REP, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + INVALID_GENDER_DESC
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE
                + TAG_DESC_MONITOR + TAG_DESC_REP, Gender.MESSAGE_CONSTRAINTS);

        // invalid involvement
        assertParseFailure(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVALID_INVOLVEMENT_DESC + OFFICE_TABLE_DESC_DEE
                + TAG_DESC_MONITOR + TAG_DESC_REP, Involvement.MESSAGE_CONSTRAINTS);

        // invalid office table number
        assertParseFailure(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + INVALID_OFFICE_TABLE_DESC
                + TAG_DESC_MONITOR + TAG_DESC_REP, OfficeTable.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE + GENDER_DESC_DEE
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE
                + INVALID_TAG_DESC + TAG_DESC_REP, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_DEE + EMAIL_DESC_DEE + INVALID_GENDER_DESC
                + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE
                + TAG_DESC_MONITOR + TAG_DESC_REP, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_DEE + PHONE_DESC_DEE + EMAIL_DESC_DEE
                + GENDER_DESC_DEE + INVOLVEMENT_DESC_DEE + OFFICE_TABLE_DESC_DEE + TAG_DESC_MONITOR + TAG_DESC_REP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeacherCommand.MESSAGE_USAGE));
    }
}
