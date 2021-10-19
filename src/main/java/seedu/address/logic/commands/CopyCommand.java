package seedu.address.logic.commands;

import java.util.List;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.person.Person;

public abstract class CopyCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Fields have been copied to clipboard!";
    public static final String MESSAGE_NOT_VALID_FIELD = "Field specified is not a valid field! Only the 'phone' "
            + "field is supported.";
    public static final String NO_FIELD_PROVIDED = "At least one field to copy must be provided. Use the c/ prefix.";
    public static final String MESSAGE_USAGE = "Just get good lol";

    private CopyCommandDescriptor copyCommandDescriptor;

    /**
     * Constructor for {@code Copycommand}
     *
     * @param copyCommandDescriptor the {@code copyCommandDescriptor to specify which field to copy}
     */

    protected CopyCommand(CopyCommandDescriptor copyCommandDescriptor) {
        this.copyCommandDescriptor = copyCommandDescriptor;
    }

    /**
     * Copies attributes of the {@code personList} to the clipboard
     *
     * @param personList the person list to copy from
     */
    public void copyToClipBoard(List<? extends Person> personList) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(getCopyContent(personList));
        clipboard.setContent(url);
    }

    /**
     * Get the string representation of the content to copy from
     *
     * @param personList person list to copy from
     * @return the string representation
     */
    public String getCopyContent(List<? extends Person> personList) {
        // TODO: convert to switch statement soon
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.PHONE)) {
            return getPhoneContent(personList);
        }
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.EMAIL)) {
            return getEmailContent(personList);
        }
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.NAME)) {
            return getNameContent(personList);
        }
        // should not reach here, invalid fields should have been filtered out while parsing
        return "";
    }

    /**
     * Get the string representation of the phone content to copy from
     *
     * @param personList the person list to copy from
     * @return the string representation
     */
    public String getPhoneContent(List<? extends Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getPhone());
            } else {
                // for phones, we put commas in case the user wants to paste it directly into whatsapp or some other
                // phone directory
                sb.append(personList.get(i).getPhone() + " ");
            }
        }
        return sb.toString();
    }

    /**
     * Get the string representation of the email content to copy from
     *
     * @param personList the person list to copy from
     * @return the string representation
     */
    public String getEmailContent(List<? extends Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getEmail());
            } else {
                // for emails, we put commas in case the user wants to paste it directly into gmail
                sb.append(personList.get(i).getEmail() + ",");
            }
        }
        return sb.toString();
    }

    /**
     * Get the string representation of the name content to copy from
     *
     * @param personList the person list to copy from
     * @return the string representation
     */
    public String getNameContent(List<? extends Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getName());
            } else {
                // for names, we put commas, else the spacing between the first and last names might be confused with
                // another name
                sb.append(personList.get(i).getName() + ", ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommand // instanceof handles nulls
                && copyCommandDescriptor.equals(((CopyCommand) other).copyCommandDescriptor)); // state check
    }
}
