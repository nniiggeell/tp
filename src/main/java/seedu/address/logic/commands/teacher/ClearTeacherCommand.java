package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the teacher address book.
 */
public class ClearTeacherCommand extends Command {

    public static final String COMMAND_WORD = "clearTeacher";
    public static final String MESSAGE_SUCCESS = "Teacher address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook newAddressBook = new AddressBook();
        // get the student list while ignoring the teacher list
        newAddressBook.setStudents(model.getAddressBook().getStudentList());
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
