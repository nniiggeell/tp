package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.teacher.DeleteTeacherCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteTeacherCommand} object
 */

public class DeleteTeacherCommandParser implements Parser<DeleteTeacherCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTeacherCommand
     * and returns a DeleteTeacherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTeacherCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTeacherCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTeacherCommand.MESSAGE_USAGE), pe);
        }
    }

}
