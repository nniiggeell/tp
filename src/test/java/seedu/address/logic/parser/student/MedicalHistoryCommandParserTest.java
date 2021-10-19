package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_HISTORY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.student.MedicalHistoryCommand;
import seedu.address.model.person.student.MedicalHistory;

public class MedicalHistoryCommandParserTest {
    private MedicalHistoryCommandParser parser = new MedicalHistoryCommandParser();
    private final String nonEmptyMedicalHistory = "Some MedicalHistory.";

    @Test
    public void parse_indexSpecified_success() {
        // have MedicalHistory
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_MEDICAL_HISTORY + nonEmptyMedicalHistory;
        MedicalHistoryCommand expectedCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(nonEmptyMedicalHistory));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no MedicalHistory
        userInput = targetIndex.getOneBased() + " " + PREFIX_MEDICAL_HISTORY;
        expectedCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT, new MedicalHistory(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MedicalHistoryCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, MedicalHistoryCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, MedicalHistoryCommand.COMMAND_WORD + " " + nonEmptyMedicalHistory, expectedMessage);
    }
}
