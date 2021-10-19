package seedu.address.logic.commands.descriptors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTeacherDescriptorBuilder;

public class EditTeacherDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTeacherDescriptor descriptorWithSameValues = new EditTeacherDescriptor(DESC_CHO);
        assertTrue(DESC_CHO.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHO.equals(DESC_CHO));

        // null -> returns false
        assertFalse(DESC_CHO.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHO.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHO.equals(DESC_DEE));

        // different name -> returns false
        EditTeacherDescriptor editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withName(VALID_NAME_DEE).build();
        assertFalse(DESC_CHO.equals(editedCho));

        // different phone -> returns false
        editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withPhone(VALID_PHONE_DEE).build();
        assertFalse(DESC_CHO.equals(editedCho));

        // different email -> returns false
        editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withEmail(VALID_EMAIL_DEE).build();
        assertFalse(DESC_CHO.equals(editedCho));

        // different gender -> returns false
        editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withGender(VALID_GENDER_DEE).build();
        assertFalse(DESC_CHO.equals(editedCho));

        // different involvement -> returns false
        editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withInvolvement(VALID_INVOLVEMENT_DEE).build();
        assertFalse(DESC_CHO.equals(editedCho));

        // different office table number -> returns false
        editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withOfficeTable(VALID_OFFICE_TABLE_DEE).build();
        assertFalse(DESC_CHO.equals(editedCho));

        // different tags -> returns false
        editedCho = new EditTeacherDescriptorBuilder(DESC_CHO).withTags(VALID_TAG_MONITOR).build();
        assertFalse(DESC_CHO.equals(editedCho));
    }
}
