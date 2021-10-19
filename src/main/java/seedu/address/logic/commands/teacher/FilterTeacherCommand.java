package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;

/**
 * Filters and lists all teachers in address book whose involvement contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterTeacherCommand extends Command {

    public static final String COMMAND_WORD = "filterTeacher";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all teachers whose involvement contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + COMMAND_WORD + " [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " donut buddies";

    private final TeacherInvolvementContainsKeywordsPredicate predicate;

    // TODO: currently, an incorrect command gives no people instead of warning the user, should we change it? ie.
    //  something like filterStudent /t rep where we use /t instead of t/ does warn the user that the command is
    //  invalid.

    public FilterTeacherCommand(TeacherInvolvementContainsKeywordsPredicate predicate) {
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
                || (other instanceof FilterTeacherCommand // instanceof handles nulls
                && predicate.equals(((FilterTeacherCommand) other).predicate)); // state check
    }
}

