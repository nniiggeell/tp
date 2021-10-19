package seedu.address.logic.commands.descriptors;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.teacher.OfficeTable;

/**
 * Stores the details to edit the teacher with. Each non-empty field value will replace the
 * corresponding field value of the teacher.
 */
public class EditTeacherDescriptor extends EditPersonDescriptor {
    private OfficeTable tableNumber;

    public EditTeacherDescriptor() {}

    /**
     * Copy constructor.
     *
     * @param toCopy {@code EditTeacherDescriptor to copy from}
     */
    public EditTeacherDescriptor(EditTeacherDescriptor toCopy) {
        super(toCopy);
        setOfficeTable(toCopy.tableNumber);
    }

    @Override
    public boolean isAnyFieldEdited() {
        return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(tableNumber);
    }

    public void setOfficeTable(OfficeTable tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Optional<OfficeTable> getOfficeTable() {
        return Optional.ofNullable(tableNumber);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTeacherDescriptor)) {
            return false;
        }

        // state check
        EditTeacherDescriptor e = (EditTeacherDescriptor) other;

        return super.equals(e) && getOfficeTable().equals(e.getOfficeTable());
    }
}
