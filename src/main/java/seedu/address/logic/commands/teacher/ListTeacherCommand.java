package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListTeacherCommand extends Command {

    public static final String COMMAND_WORD = "listTeachers";

    public static final String MESSAGE_SUCCESS = "Listed all teachers";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeacherList(Model.PREDICATE_SHOW_ALL_TEACHERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
