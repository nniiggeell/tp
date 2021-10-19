package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Undos the previous operation in the model manager.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Previous action has been undone!";
    public static final String MESSAGE_ALREADY_AT_OLDEST_CHANGE = "Already at oldest change!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        boolean success = model.undo();
        if (success) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_ALREADY_AT_OLDEST_CHANGE);
        }
    }
}
