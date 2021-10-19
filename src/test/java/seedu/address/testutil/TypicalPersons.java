package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FORM_CLASS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FORM_CLASS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;

/**
 * A utility class containing a list of {@code Student} and {@code Teacher} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Student ALICE = new StudentBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withEmergencyContact("95614132")
            .withInvolvement("Math class")
            .withFormClass("4N1")
            .withGender("F")
            .withMedicalHistory("ADHD")
            .build();
    public static final Student BENSON = new StudentBuilder()
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withEmergencyContact("91939100")
            .withInvolvement("Math class")
            .withFormClass("3E3")
            .withGender("M")
            .withMedicalHistory("Autistic")
            .build();
    public static final Student CARL = new StudentBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withEmergencyContact("48190514")
            .withInvolvement("Form class")
            .withFormClass("4E2")
            .withGender("M")
            .withMedicalHistory("")
            .build();
    public static final Student DANIEL = new StudentBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withEmergencyContact("58141834")
            .withInvolvement("Form class")
            .withFormClass("4E2")
            .withGender("M")
            .withMedicalHistory("")
            .build();
    public static final Student ELLE = new StudentBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withEmergencyContact("92329700")
            .withInvolvement("Form class")
            .withFormClass("4E2")
            .withGender("F")
            .withMedicalHistory("")
            .build();
    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withEmergencyContact("580947918074907019749087")
            .withInvolvement("English class")
            .withFormClass("1T1")
            .withGender("F")
            .withMedicalHistory("")
            .build();
    public static final Student GEORGE = new StudentBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withEmergencyContact("84041748901")
            .withInvolvement("English class")
            .withFormClass("1T1")
            .withGender("M")
            .withMedicalHistory("")
            .build();

    public static final Student FISH_NOT_IN_LIST = new StudentBuilder()
            .withName("Fish")
            .withPhone("18941908")
            .withEmail("fish@thebottomofthesea.com")
            .withAddress("Saltwater lakes")
            .withEmergencyContact("4314123414141")
            .withInvolvement("Swimming class")
            .withFormClass("1T2")
            .withGender("M")
            .withMedicalHistory("Unable to live on land")
            .build();

    public static final Student EDITED_FISH_NOT_IN_LIST = new StudentBuilder()
            .withName("Fish")
            .withPhone("57901790417") // phone is edited
            .withEmail("fish@thebottomofthesea.com")
            .withAddress("Saltwater lakes")
            .withEmergencyContact("4314123414141")
            .withInvolvement("Swimming class")
            .withFormClass("1T2")
            .withGender("M")
            .withMedicalHistory("Unable to live on land")
            .build();

    public static final Student CARP_NOT_IN_LIST = new StudentBuilder()
            .withName("Carp")
            .withPhone("18943411908")
            .withEmail("carp@thebottomofthesea.com")
            .withAddress("Freshwater lakes")
            .withEmergencyContact("4123414141")
            .withInvolvement("Swimming class")
            .withFormClass("1T2")
            .withGender("F")
            .withMedicalHistory("Unable to live on land")
            .build();

    public static final Teacher ALI = new TeacherBuilder()
            .withName("Ali Paula")
            .withPhone("94351253")
            .withEmail("ali@example.com")
            .withGender("F")
            .withInvolvement("Math Dept")
            .withOfficeTable("1")
            .withTags("friends")
            .build();

    public static final Teacher BEN = new TeacherBuilder()
            .withName("Ben ten")
            .withPhone("98765432")
            .withEmail("bm1@example.com")
            .withGender("M")
            .withInvolvement("Math Dept")
            .withOfficeTable("2")
            .withTags("owesMoney", "friends")
            .build();

    public static final Teacher CAR = new TeacherBuilder()
            .withName("Car goh")
            .withPhone("95352563")
            .withEmail("ck@example.com")
            .withGender("M")
            .withInvolvement("4A Co-Form")
            .withOfficeTable("3")
            .build();

    public static final Teacher DAN = new TeacherBuilder()
            .withName("Dan Mei")
            .withPhone("87652533")
            .withEmail("dm2@example.com")
            .withGender("M")
            .withInvolvement("4A Co-Form")
            .withOfficeTable("4")
            .withTags("friends")
            .build();

    public static final Teacher EL = new TeacherBuilder()
            .withName("El Mey")
            .withPhone("9482224")
            .withEmail("em@example.com")
            .withGender("F")
            .withInvolvement("Math Dept")
            .withOfficeTable("5")
            .build();

    public static final Teacher FIO = new TeacherBuilder()
            .withName("Fio runz")
            .withPhone("9482427")
            .withEmail("fk@example.com")
            .withGender("F")
            .withInvolvement("Math Dept")
            .withOfficeTable("6")
            .build();

    public static final Teacher GEO = new TeacherBuilder()
            .withName("Geo Good")
            .withPhone("9482442")
            .withEmail("best@example.com")
            .withGender("M")
            .withInvolvement("Math HOD")
            .withOfficeTable("7")
            .build();

    // Manually added
    public static final Student HOON = new StudentBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withEmergencyContact("91939100")
            .withInvolvement("English rep")
            .withFormClass("3E2")
            .withGender("N")
            .withMedicalHistory("")
            .build();

    public static final Student IDA = new StudentBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withEmergencyContact("91939100")
            .withInvolvement("Bio rep")
            .withFormClass("2T1")
            .withGender("F")
            .withMedicalHistory("")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withInvolvement(VALID_INVOLVEMENT_AMY)
            .withTags(VALID_TAG_REP)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY)
            .withFormClass(VALID_FORM_CLASS_AMY)
            .withGender(VALID_GENDER_AMY)
            .withMedicalHistory("")
            .build();

    public static final Student BOB = new StudentBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withInvolvement(VALID_INVOLVEMENT_BOB)
            .withTags(VALID_TAG_MONITOR, VALID_TAG_REP)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB)
            .withFormClass(VALID_FORM_CLASS_BOB)
            .withGender(VALID_GENDER_BOB)
            .withMedicalHistory("")
            .build();

    public static final Teacher CHO = new TeacherBuilder()
        .withName(VALID_NAME_CHO)
        .withPhone(VALID_PHONE_CHO)
        .withEmail(VALID_EMAIL_CHO)
        .withGender(VALID_GENDER_CHO)
        .withInvolvement(VALID_INVOLVEMENT_CHO)
        .withOfficeTable(VALID_OFFICE_TABLE_CHO)
        .withTags(VALID_TAG_REP)
        .build();

    public static final Teacher DEE = new TeacherBuilder()
            .withName(VALID_NAME_DEE)
            .withPhone(VALID_PHONE_DEE)
            .withEmail(VALID_EMAIL_DEE)
            .withGender(VALID_GENDER_DEE)
            .withInvolvement(VALID_INVOLVEMENT_DEE)
            .withOfficeTable(VALID_OFFICE_TABLE_DEE)
            .withTags(VALID_TAG_MONITOR, VALID_TAG_REP)
            .build();

    public static final Teacher EXTRA_TEACHER_NOT_IN_LIST = new TeacherBuilder()
            .withName("Nur teacher")
            .withPhone("9482442")
            .withEmail("best@example.com")
            .withGender("M")
            .withInvolvement("Math HOD")
            .withOfficeTable("7")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students and teachers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (Teacher teacher: getTypicalTeachers()) {
            ab.addTeacher(teacher);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBookStudents() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBookTeachers() {
        AddressBook ab = new AddressBook();
        for (Teacher teacher : getTypicalTeachers()) {
            ab.addTeacher(teacher);
        }
        return ab;
    }


    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Teacher> getTypicalTeachers() {
        return new ArrayList<>(Arrays.asList(ALI, BEN, CAR, DAN, EL, FIO, GEO));
    }
}
