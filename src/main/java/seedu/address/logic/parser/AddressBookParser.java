package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.logic.commands.student.ClearStudentCommand;
import seedu.address.logic.commands.student.CopyStudentCommand;
import seedu.address.logic.commands.student.DeleteStudentCommand;
import seedu.address.logic.commands.student.EditStudentCommand;
import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.logic.commands.student.FindStudentCommand;
import seedu.address.logic.commands.student.ListStudentCommand;
import seedu.address.logic.commands.student.MedicalHistoryCommand;
import seedu.address.logic.commands.teacher.AddTeacherCommand;
import seedu.address.logic.commands.teacher.ClearTeacherCommand;
import seedu.address.logic.commands.teacher.CopyTeacherCommand;
import seedu.address.logic.commands.teacher.DeleteTeacherCommand;
import seedu.address.logic.commands.teacher.EditTeacherCommand;
import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.logic.commands.teacher.FindTeacherCommand;
import seedu.address.logic.commands.teacher.ListTeacherCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.student.AddStudentCommandParser;
import seedu.address.logic.parser.student.CopyStudentCommandParser;
import seedu.address.logic.parser.student.DeleteStudentCommandParser;
import seedu.address.logic.parser.student.EditStudentCommandParser;
import seedu.address.logic.parser.student.FilterStudentCommandParser;
import seedu.address.logic.parser.student.FindStudentCommandParser;
import seedu.address.logic.parser.student.MedicalHistoryCommandParser;
import seedu.address.logic.parser.teacher.AddTeacherCommandParser;
import seedu.address.logic.parser.teacher.CopyTeacherCommandParser;
import seedu.address.logic.parser.teacher.DeleteTeacherCommandParser;
import seedu.address.logic.parser.teacher.EditTeacherCommandParser;
import seedu.address.logic.parser.teacher.FilterTeacherCommandParser;
import seedu.address.logic.parser.teacher.FindTeacherCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case AddTeacherCommand.COMMAND_WORD:
            return new AddTeacherCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        case EditTeacherCommand.COMMAND_WORD:
            return new EditTeacherCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteTeacherCommand.COMMAND_WORD:
            return new DeleteTeacherCommandParser().parse(arguments);

        case ClearStudentCommand.COMMAND_WORD:
            return new ClearStudentCommand();

        case ClearTeacherCommand.COMMAND_WORD:
            return new ClearTeacherCommand();

        case FindStudentCommand.COMMAND_WORD:
            return new FindStudentCommandParser().parse(arguments);

        case FindTeacherCommand.COMMAND_WORD:
            return new FindTeacherCommandParser().parse(arguments);

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case ListTeacherCommand.COMMAND_WORD:
            return new ListTeacherCommand();

        case CopyStudentCommand.COMMAND_WORD:
            return new CopyStudentCommandParser().parse(arguments);

        case CopyTeacherCommand.COMMAND_WORD:
            return new CopyTeacherCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FilterStudentCommand.COMMAND_WORD:
            return new FilterStudentCommandParser().parse(arguments);

        case FilterTeacherCommand.COMMAND_WORD:
            return new FilterTeacherCommandParser().parse(arguments);

        case MedicalHistoryCommand.COMMAND_WORD: // TODO: check that medical history only updates for students?
            return new MedicalHistoryCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
