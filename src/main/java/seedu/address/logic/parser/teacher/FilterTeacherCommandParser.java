package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code FilterTeacherCommand} object
 */

public class FilterTeacherCommandParser implements Parser<FilterTeacherCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTeacherCommand
     * and returns a FilterTeacherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTeacherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTeacherCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterTeacherCommand(new TeacherInvolvementContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
