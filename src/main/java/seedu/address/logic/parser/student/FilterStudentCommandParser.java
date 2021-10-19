package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code FilterStudentCommand} object
 */

public class FilterStudentCommandParser implements Parser<FilterStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterStudentCommand
     * and returns a FilterStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterStudentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterStudentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterStudentCommand(new StudentInvolvementContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
