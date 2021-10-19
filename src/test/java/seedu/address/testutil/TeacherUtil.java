package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INVOLVEMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE_TABLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.logic.commands.teacher.AddTeacherCommand;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Teacher.
 */

public class TeacherUtil {
    /**
     * Returns an add command string for adding the {@code teacher}.
     */
    public static String getTeacherCommand(Teacher teacher) {
        return AddTeacherCommand.COMMAND_WORD + " " + getTeacherDetails(teacher);
    }

    /**
     * Returns the part of command string for the given {@code teachers}'s details.
     */
    public static String getTeacherDetails(Teacher teacher) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + teacher.getName().fullName + " ");
        sb.append(PREFIX_PHONE + teacher.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + teacher.getEmail().value + " ");
        sb.append(PREFIX_GENDER + teacher.getGender().value + " ");
        sb.append(PREFIX_INVOLVEMENT + teacher.getInvolvement().value + " ");
        sb.append(PREFIX_OFFICE_TABLE + teacher.getOfficeTable().value + " ");
        teacher.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code teachers}'s details.
     */
    public static String getEditTeacherDescriptorDetails(EditTeacherDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getGender().ifPresent(genders -> sb.append(PREFIX_GENDER).append(genders.value).append(" "));
        descriptor.getInvolvement().ifPresent(involvement -> sb.append(PREFIX_INVOLVEMENT)
                .append(involvement.value).append(" "));
        descriptor.getOfficeTable().ifPresent(officeTable -> sb.append(PREFIX_OFFICE_TABLE)
                .append(officeTable.value).append(" "));

        // tags should be the last, else bug
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
