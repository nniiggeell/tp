package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REP;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.DEE;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.teacher.Teacher;
import seedu.address.testutil.TeacherBuilder;

public class TeacherTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Teacher teacher = new TeacherBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> teacher.getTags().remove(0));
    }

    @Test
    public void isSameTeacher() {
        // same object -> returns true
        assertTrue(ALI.isSamePerson(ALI));

        // null -> returns false
        assertFalse(ALI.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Teacher editedAli = new TeacherBuilder(ALI).withPhone(VALID_PHONE_DEE).withEmail(VALID_EMAIL_DEE)
                .withGender(VALID_GENDER_DEE).withInvolvement(VALID_INVOLVEMENT_DEE)
                .withTags(VALID_TAG_REP).build();
        assertTrue(ALI.isSamePerson(editedAli));

        // different name, all other attributes same -> returns true
        editedAli = new TeacherBuilder(ALI).withName(VALID_NAME_DEE).build();
        assertFalse(ALI.isSamePerson(editedAli));

        // name differs in case, all other attributes same -> returns false
        Teacher editedDee = new TeacherBuilder(DEE).withName(VALID_NAME_DEE.toLowerCase()).build();
        assertFalse(DEE.isSamePerson(editedDee));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_DEE + " ";
        editedDee = new TeacherBuilder(DEE).withName(nameWithTrailingSpaces).build();
        assertFalse(DEE.isSamePerson(editedDee));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Teacher aliCopy = new TeacherBuilder(ALI).build();
        assertTrue(ALI.equals(aliCopy));

        // same object -> returns true
        assertTrue(ALI.equals(ALI));

        // null -> returns false
        assertFalse(ALI.equals(null));

        // different type -> returns false
        assertFalse(ALI.equals(5));

        // different person -> returns false
        assertFalse(ALI.equals(DEE));

        // different name -> returns false
        Teacher editedAli = new TeacherBuilder(ALI).withName(VALID_NAME_DEE).build();
        assertFalse(ALI.equals(editedAli));

        // different phone -> returns false
        editedAli = new TeacherBuilder(ALI).withPhone(VALID_PHONE_DEE).build();
        assertFalse(ALI.equals(editedAli));

        // different email -> returns false
        editedAli = new TeacherBuilder(ALI).withEmail(VALID_EMAIL_DEE).build();
        assertFalse(ALI.equals(editedAli));

        // different gender -> returns false
        editedAli = new TeacherBuilder(ALI).withGender(VALID_GENDER_DEE).build();
        assertFalse(ALI.equals(editedAli));

        // different involvement -> returns false
        editedAli = new TeacherBuilder(ALI).withInvolvement(VALID_INVOLVEMENT_DEE).build();
        assertFalse(ALI.equals(editedAli));

        // different office table number -> returns false
        editedAli = new TeacherBuilder(ALI).withOfficeTable(VALID_OFFICE_TABLE_DEE).build();
        assertFalse(ALI.equals(editedAli));

        // different tags -> returns false
        editedAli = new TeacherBuilder(ALI).withTags(VALID_TAG_REP).build();
        assertFalse(ALI.equals(editedAli));
    }
}
