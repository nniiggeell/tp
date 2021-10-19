package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditTeacherDescriptor objects.
 */
public class EditTeacherDescriptorBuilder {

    private EditTeacherDescriptor descriptor;

    public EditTeacherDescriptorBuilder() {
        descriptor = new EditTeacherDescriptor();
    }

    public EditTeacherDescriptorBuilder(EditTeacherDescriptor descriptor) {
        this.descriptor = new EditTeacherDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTeacherDescriptor} with fields containing {@code teacher}'s details
     */
    public EditTeacherDescriptorBuilder(Teacher teacher) {
        descriptor = new EditTeacherDescriptor();
        descriptor.setName(teacher.getName());
        descriptor.setPhone(teacher.getPhone());
        descriptor.setEmail(teacher.getEmail());
        descriptor.setGender(teacher.getGender());
        descriptor.setInvolvement(teacher.getInvolvement());
        descriptor.setOfficeTable(teacher.getOfficeTable());
        descriptor.setTags(teacher.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTeacherDescriptor} that we are building.
     */
    public EditTeacherDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTeacherDescriptor} that we are building.
     */
    public EditTeacherDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTeacherDescriptor} that we are building.
     */
    public EditTeacherDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditTeacherDescriptor} that we are building.
     */
    public EditTeacherDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Involvement} of the {@code EditTeacherDescriptor} that we are building.
     */
    public EditTeacherDescriptorBuilder withInvolvement(String involvement) {
        descriptor.setInvolvement(new Involvement(involvement));
        return this;
    }

    /**
     * Sets the {@code OfficeTable} of the {@code EditTeacherDescriptor} that we are building.
     */
    public EditTeacherDescriptorBuilder withOfficeTable(String officeTable) {
        descriptor.setOfficeTable(new OfficeTable(officeTable));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTeacherDescriptor}
     * that we are building.
     */
    public EditTeacherDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTeacherDescriptor build() {
        return descriptor;
    }
}
