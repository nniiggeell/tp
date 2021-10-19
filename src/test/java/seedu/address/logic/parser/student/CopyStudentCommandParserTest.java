package seedu.address.logic.parser.student;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COPY_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.logic.commands.student.CopyStudentCommand;

public class CopyStudentCommandParserTest {

    private static final String EMPTY_FIELD = " " + PREFIX_COPY_FIELD;

    private static final String VALID_FIELD_PHONE = " " + PREFIX_COPY_FIELD + "phone";
    private static final String VALID_FIELD_EMAIL = " " + PREFIX_COPY_FIELD + "email";
    private static final String VALID_FIELD_WITH_WHITESPACE = " " + PREFIX_COPY_FIELD + "  phone";

    private static final String INVALID_FIELD = " " + PREFIX_COPY_FIELD + "fish";
    private static final String INVALID_PREFIX = " " + PREFIX_GENDER + "phone";

    private CopyStudentCommandParser parser = new CopyStudentCommandParser();

    @Test
    public void parse_missingCopyField_failure() {
        // c/ is present, but has empty argument
        assertParseFailure(parser, EMPTY_FIELD, CopyCommand.MESSAGE_NOT_VALID_FIELD);

        // c/ is present, but argument is invalid
        assertParseFailure(parser, INVALID_FIELD, CopyCommand.MESSAGE_NOT_VALID_FIELD);
    }

    @Test
    public void parse_missingFields_failure() {
        // no field specified, c/ prefix mixed up with something else
        assertParseFailure(parser, INVALID_PREFIX, CopyCommand.NO_FIELD_PROVIDED + "\n" + CopyCommand.MESSAGE_USAGE);

        // no prefix at all
        assertParseFailure(parser, "", CopyCommand.NO_FIELD_PROVIDED + "\n" + CopyCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validFields_success() {
        assertParseSuccess(parser, VALID_FIELD_PHONE, new CopyStudentCommand(new CopyCommandDescriptor("phone")));
        assertParseSuccess(parser, VALID_FIELD_EMAIL, new CopyStudentCommand(new CopyCommandDescriptor("email")));

        // check whitespace
        assertParseSuccess(parser, VALID_FIELD_WITH_WHITESPACE, new CopyStudentCommand(new CopyCommandDescriptor(
                "phone")));
    }
}
