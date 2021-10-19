package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;

public class CopyStudentCommand extends CopyCommand {

    public static final String COMMAND_WORD = "copyStudent";

    /**
     * Constructor for {@code CopyStudentCommand}
     *
     * @param copyCommandDescriptor the {@code copyCommandDescriptor to specify which field to copy}
     */

    public CopyStudentCommand(CopyCommandDescriptor copyCommandDescriptor) {
        super(copyCommandDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        copyToClipBoard(lastShownList);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyStudentCommand // instanceof handles nulls
                && super.equals(other));
    }

}
