package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the student address book.
 */
public class ClearStudentCommand extends Command {

    public static final String COMMAND_WORD = "clearStudent";
    public static final String MESSAGE_SUCCESS = "Student Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook newAddressBook = new AddressBook();
        // get the teacher list while ignoring the student list
        newAddressBook.setTeachers(model.getAddressBook().getTeacherList());
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
