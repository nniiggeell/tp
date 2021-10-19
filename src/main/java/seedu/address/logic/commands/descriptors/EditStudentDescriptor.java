package seedu.address.logic.commands.descriptors;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Address;
import seedu.address.model.person.student.FormClass;

public class EditStudentDescriptor extends EditPersonDescriptor {
    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    private Phone emergencyContact;
    private FormClass formClass;
    private Address address;

    public EditStudentDescriptor() {}

    /**
     * Copy constructor
     *
     * @param toCopy {@code EditStudentDescriptor to copy from}
     */
    public EditStudentDescriptor(EditStudentDescriptor toCopy) {
        super(toCopy);
        setEmergencyContact(toCopy.emergencyContact);
        setFormClass(toCopy.formClass);
        setAddress(toCopy.address);
    }

    public void setEmergencyContact(Phone phone) {
        this.emergencyContact = phone;
    }

    public Optional<Phone> getEmergencyContact() {
        return Optional.ofNullable(emergencyContact);
    }

    public void setFormClass(FormClass formClass) {
        this.formClass = formClass;
    }

    public Optional<FormClass> getFormClass() {
        return Optional.ofNullable(formClass);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    @Override
    public boolean isAnyFieldEdited() {
        return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(emergencyContact, formClass, address);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentDescriptor)) {
            return false;
        }

        // state check
        EditStudentDescriptor e = (EditStudentDescriptor) other;

        return super.equals(e)
                && getEmergencyContact().equals(e.getEmergencyContact())
                && getFormClass().equals(e.getFormClass())
                && getAddress().equals(e.getAddress());
    }

}
