package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.MedicalHistory;
import seedu.address.model.person.student.Student;

/**
 * Changes the medical history of an existing student inside the New Address Book.
 */
public class MedicalHistoryCommand extends Command {
    public static final String COMMAND_WORD = "medical";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the medical history of the student identified "
            + "by the index number used in the last student listing. "
            + "Existing medical history will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "m/ [MEDICAL_HISTORY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "m/ ADHD";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Medical_History: %2$s";
    public static final String MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS = "Added medical history to Student: %1$s";
    public static final String MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS = "Removed medical history from Student: %1$s";

    private final Index index;
    private final MedicalHistory medicalHistory;

    /**
     * @param index of the student in the filtered student list to edit the {@code MedicalHistory}
     * @param medicalHistory of the student to be updated to
     */
    public MedicalHistoryCommand(Index index, MedicalHistory medicalHistory) {
        requireAllNonNull(index, medicalHistory);

        this.index = index;
        this.medicalHistory = medicalHistory;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(studentToEdit, studentToEdit.getEmergencyContact(),
            studentToEdit.getFormClass(), studentToEdit.getAddress(), medicalHistory);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the medical history is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !medicalHistory.value.isEmpty() ? MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS
                : MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalHistoryCommand)) {
            return false;
        }

        // state check
        MedicalHistoryCommand e = (MedicalHistoryCommand) other;
        return index.equals(e.index)
                && medicalHistory.equals(e.medicalHistory);
    }
}
