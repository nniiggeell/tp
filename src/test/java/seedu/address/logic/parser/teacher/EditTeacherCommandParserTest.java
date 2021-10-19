package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INVOLVEMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OFFICE_TABLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVOLVEMENT_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_TABLE_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_TABLE_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEACHER;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TEACHER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.logic.commands.teacher.EditTeacherCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditTeacherDescriptorBuilder;

public class EditTeacherCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeacherCommand.MESSAGE_USAGE);

    private EditTeacherCommandParser parser = new EditTeacherCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_CHO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CHO, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CHO, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 b/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_OFFICE_TABLE_DESC,
                OfficeTable.MESSAGE_CONSTRAINTS); // invalid office table number
        assertParseFailure(parser, "1" + INVALID_INVOLVEMENT_DESC,
                Involvement.MESSAGE_CONSTRAINTS); //invalid involvement

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_CHO, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_DEE + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_REP + TAG_DESC_MONITOR + TAG_EMPTY,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_REP + TAG_EMPTY + TAG_DESC_MONITOR,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_REP + TAG_DESC_MONITOR,
            Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_OFFICE_TABLE_CHO
            + VALID_PHONE_CHO, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TEACHER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_DEE + TAG_DESC_MONITOR
            + EMAIL_DESC_CHO + NAME_DESC_CHO + TAG_DESC_REP + OFFICE_TABLE_DESC_CHO
            + INVOLVEMENT_DESC_CHO + GENDER_DESC_CHO;

        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withName(VALID_NAME_CHO)
            .withPhone(VALID_PHONE_DEE).withEmail(VALID_EMAIL_CHO).withGender(VALID_GENDER_CHO)
            .withInvolvement(VALID_INVOLVEMENT_CHO).withOfficeTable(VALID_OFFICE_TABLE_CHO)
            .withTags(VALID_TAG_MONITOR, VALID_TAG_REP).build();
        EditTeacherCommand expectedCommand = new EditTeacherCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TEACHER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_DEE + EMAIL_DESC_CHO;

        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withPhone(VALID_PHONE_DEE)
            .withEmail(VALID_EMAIL_CHO).build();
        EditTeacherCommand expectedCommand = new EditTeacherCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TEACHER;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CHO;
        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withName(VALID_NAME_CHO).build();
        EditTeacherCommand expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_CHO;
        descriptor = new EditTeacherDescriptorBuilder().withPhone(VALID_PHONE_CHO).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_CHO;
        descriptor = new EditTeacherDescriptorBuilder().withEmail(VALID_EMAIL_CHO).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // involvement
        userInput = targetIndex.getOneBased() + INVOLVEMENT_DESC_CHO;
        descriptor = new EditTeacherDescriptorBuilder().withInvolvement(VALID_INVOLVEMENT_CHO).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // office table number
        userInput = targetIndex.getOneBased() + OFFICE_TABLE_DESC_CHO;
        descriptor = new EditTeacherDescriptorBuilder().withOfficeTable(VALID_OFFICE_TABLE_CHO).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = targetIndex.getOneBased() + GENDER_DESC_CHO;
        descriptor = new EditTeacherDescriptorBuilder().withGender(VALID_GENDER_CHO).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_REP;
        descriptor = new EditTeacherDescriptorBuilder().withTags(VALID_TAG_REP).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TEACHER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_CHO + EMAIL_DESC_CHO + OFFICE_TABLE_DESC_CHO
            + TAG_DESC_REP + PHONE_DESC_CHO + OFFICE_TABLE_DESC_CHO + EMAIL_DESC_CHO + TAG_DESC_REP
            + PHONE_DESC_CHO + EMAIL_DESC_CHO + TAG_DESC_MONITOR;

        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withPhone(VALID_PHONE_CHO)
            .withEmail(VALID_EMAIL_CHO).withOfficeTable(VALID_OFFICE_TABLE_CHO)
            .withTags(VALID_TAG_REP, VALID_TAG_MONITOR).build();
        EditCommand expectedCommand = new EditTeacherCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TEACHER;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_DEE;
        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withPhone(VALID_PHONE_DEE).build();
        EditTeacherCommand expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_DEE + INVALID_PHONE_DESC + OFFICE_TABLE_DESC_DEE
            + PHONE_DESC_DEE;
        descriptor = new EditTeacherDescriptorBuilder().withPhone(VALID_PHONE_DEE).withEmail(VALID_EMAIL_DEE)
            .withOfficeTable(VALID_OFFICE_TABLE_DEE).build();
        expectedCommand = new EditTeacherCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_TEACHER;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder().withTags().build();
        EditTeacherCommand expectedCommand = new EditTeacherCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
