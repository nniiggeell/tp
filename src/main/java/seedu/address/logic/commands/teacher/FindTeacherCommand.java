package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.teacher.TeacherNameContainsKeywordsPredicate;

public class FindTeacherCommand extends Command {

    public static final String COMMAND_WORD = "findTeacher";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all teachers whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final TeacherNameContainsKeywordsPredicate predicate;

    public FindTeacherCommand(TeacherNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeacherList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TEACHERS_LISTED_OVERVIEW, model.getFilteredTeacherList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTeacherCommand // instanceof handles nulls
                && predicate.equals(((FindTeacherCommand) other).predicate)); // state check
    }
}

