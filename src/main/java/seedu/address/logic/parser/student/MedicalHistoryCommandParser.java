package seedu.address.logic.parser.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_HISTORY;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.student.MedicalHistoryCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.student.MedicalHistory;

/**
 * Parses input arguments and creates a new {@code MedicalHistoryCommand} object.
 */

public class MedicalHistoryCommandParser implements Parser<MedicalHistoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MedicalHistoryCommand
     * and returns an MedicalHistoryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MedicalHistoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_MEDICAL_HISTORY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MedicalHistoryCommand.MESSAGE_USAGE), ive);
        }

        String medicalHistory = argMultimap.getValue(PREFIX_MEDICAL_HISTORY).orElse("");

        return new MedicalHistoryCommand(index, new MedicalHistory(medicalHistory));
    }
}
