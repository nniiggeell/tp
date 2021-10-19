package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORM_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INVOLVEMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE_TABLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.descriptors.EditStudentDescriptor;
import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.person.teacher.TeacherNameContainsKeywordsPredicate;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTeacherDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // students
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHO = "Cho li";
    public static final String VALID_NAME_DEE = "Dee ku";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CHO = "33333333";
    public static final String VALID_PHONE_DEE = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CHO = "cho@example.com";
    public static final String VALID_EMAIL_DEE = "dee@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_MONITOR = "monitor";
    public static final String VALID_TAG_REP = "rep";
    public static final String VALID_EMERGENCY_CONTACT_AMY = "914134304141";
    public static final String VALID_EMERGENCY_CONTACT_BOB = "91304141";
    public static final String VALID_INVOLVEMENT_AMY = "Bio class";
    public static final String VALID_INVOLVEMENT_BOB = "Dance";
    public static final String VALID_INVOLVEMENT_CHO = "PE Dept";
    public static final String VALID_INVOLVEMENT_DEE = "4A Co-Form";
    public static final String VALID_FORM_CLASS_AMY = "4E1";
    public static final String VALID_FORM_CLASS_BOB = "2E1";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_GENDER_CHO = "F";
    public static final String VALID_GENDER_DEE = "N";
    public static final String VALID_OFFICE_TABLE_CHO = "4";
    public static final String VALID_OFFICE_TABLE_DEE = "12";
    public static final String VALID_MEDICAL_HISTORY_AMY = "ADHD";
    public static final String VALID_MEDICAL_HISTORY_BOB = "Autistic";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CHO = " " + PREFIX_NAME + VALID_NAME_CHO;
    public static final String NAME_DESC_DEE = " " + PREFIX_NAME + VALID_NAME_DEE;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_CHO = " " + PREFIX_PHONE + VALID_PHONE_CHO;
    public static final String PHONE_DESC_DEE = " " + PREFIX_PHONE + VALID_PHONE_DEE;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CHO = " " + PREFIX_EMAIL + VALID_EMAIL_CHO;
    public static final String EMAIL_DESC_DEE = " " + PREFIX_EMAIL + VALID_EMAIL_DEE;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String GENDER_DESC_CHO = " " + PREFIX_GENDER + VALID_GENDER_CHO;
    public static final String GENDER_DESC_DEE = " " + PREFIX_GENDER + VALID_GENDER_DEE;
    public static final String INVOLVEMENT_DESC_AMY = " " + PREFIX_INVOLVEMENT + VALID_INVOLVEMENT_AMY;
    public static final String INVOLVEMENT_DESC_BOB = " " + PREFIX_INVOLVEMENT + VALID_INVOLVEMENT_BOB;
    public static final String INVOLVEMENT_DESC_CHO = " " + PREFIX_INVOLVEMENT + VALID_INVOLVEMENT_CHO;
    public static final String INVOLVEMENT_DESC_DEE = " " + PREFIX_INVOLVEMENT + VALID_INVOLVEMENT_DEE;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String EMERGENCY_CONTACT_DESC_AMY = " " + PREFIX_EMERGENCY_CONTACT
            + VALID_EMERGENCY_CONTACT_AMY;
    public static final String EMERGENCY_CONTACT_DESC_BOB =
            " " + PREFIX_EMERGENCY_CONTACT + VALID_EMERGENCY_CONTACT_BOB;
    public static final String TAG_DESC_REP = " " + PREFIX_TAG + VALID_TAG_REP;
    public static final String TAG_DESC_MONITOR = " " + PREFIX_TAG + VALID_TAG_MONITOR;
    public static final String FORM_CLASS_DESC_AMY = " " + PREFIX_FORM_CLASS + VALID_FORM_CLASS_AMY;
    public static final String FORM_CLASS_DESC_BOB = " " + PREFIX_FORM_CLASS + VALID_FORM_CLASS_BOB;
    public static final String OFFICE_TABLE_DESC_CHO = " " + PREFIX_OFFICE_TABLE + VALID_OFFICE_TABLE_CHO;
    public static final String OFFICE_TABLE_DESC_DEE = " " + PREFIX_OFFICE_TABLE + VALID_OFFICE_TABLE_DEE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_EMERGENCY_CONTACT_DESC = " " + PREFIX_EMERGENCY_CONTACT + "911232a"; // 'a' not
    // allowed in phones
    public static final String INVALID_INVOLVEMENT_DESC = " " + PREFIX_INVOLVEMENT; // empty string not allowed
    public static final String INVALID_FORM_CLASS_DESC = " "
            + PREFIX_FORM_CLASS + ""; // empty string not allowed
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "d"; //Anything other than M/F/N (not allow)
    public static final String INVALID_OFFICE_TABLE_DESC = " " + PREFIX_OFFICE_TABLE + "12a"; // 'a' not allowed in
    // office table number
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentDescriptor DESC_AMY;
    public static final EditStudentDescriptor DESC_BOB;
    public static final EditTeacherDescriptor DESC_CHO;
    public static final EditTeacherDescriptor DESC_DEE;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder()
                .withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY)
                .withFormClass(VALID_FORM_CLASS_AMY)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withInvolvement(VALID_INVOLVEMENT_AMY)
                .withTags(VALID_TAG_REP)
                .withGender(VALID_GENDER_AMY)
                .build();
        DESC_BOB = new EditStudentDescriptorBuilder()
                .withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB)
                .withFormClass(VALID_FORM_CLASS_BOB)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withInvolvement(VALID_INVOLVEMENT_AMY)
                .withTags(VALID_TAG_MONITOR, VALID_TAG_REP)
                .withGender(VALID_GENDER_BOB)
                .build();
        DESC_CHO = new EditTeacherDescriptorBuilder()
                .withName(VALID_NAME_CHO)
                .withPhone(VALID_PHONE_CHO)
                .withEmail(VALID_EMAIL_CHO)
                .withGender(VALID_GENDER_CHO)
                .withInvolvement(VALID_INVOLVEMENT_CHO)
                .withOfficeTable(VALID_OFFICE_TABLE_CHO)
                .withTags(VALID_TAG_REP, VALID_TAG_MONITOR)
                .build();
        DESC_DEE = new EditTeacherDescriptorBuilder()
                .withName(VALID_NAME_DEE)
                .withPhone(VALID_PHONE_DEE)
                .withEmail(VALID_EMAIL_DEE)
                .withGender(VALID_GENDER_DEE)
                .withInvolvement(VALID_INVOLVEMENT_DEE)
                .withOfficeTable(VALID_OFFICE_TABLE_DEE)
                .withTags(VALID_TAG_REP, VALID_TAG_MONITOR)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered teacher and student list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Teacher> expectedTeacherFilteredList = new ArrayList<>(actualModel.getFilteredTeacherList());
        List<Student> expectedStudentFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedTeacherFilteredList, actualModel.getFilteredTeacherList());
        assertEquals(expectedStudentFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new StudentNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the teacher at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTeacherAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTeacherList().size());

        Teacher teacher = model.getFilteredTeacherList().get(targetIndex.getZeroBased());
        final String[] splitName = teacher.getName().fullName.split("\\s+");
        model.updateFilteredTeacherList(new TeacherNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTeacherList().size());
    }

}
