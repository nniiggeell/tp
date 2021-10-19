package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Address;
import seedu.address.model.person.student.FormClass;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Gender("M"), new Involvement("Class 4A"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Phone("91934131"), new FormClass("1E1"), getTagSet("monitor")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Gender("F"), new Involvement("Math class"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Phone("95614132"), new FormClass("1E4"), getTagSet("rep")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Gender("F"), new Involvement("Bio class"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Phone("90234134"), new FormClass("3B5"), getTagSet("rep"))
        };
    }

    public static Teacher[] getSampleTeachers() {
        return new Teacher[] {
            new Teacher(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Gender("M"), new Involvement("class 4A Co-Form"), new OfficeTable("1"),
                    getTagSet("family")),
            new Teacher(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Gender("N"), new Involvement("Math HOD"), new OfficeTable("2"),
                    getTagSet("Colleague")),
            new Teacher(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Gender("M"), new Involvement("Math Department"), new OfficeTable("3"),
                    getTagSet("lunchbuddy"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(new DateTime("2060-01-02 11:12"), new Attendee("T"),
                    new Description("Meeting with Math Department"), new Description("Meeting room 1")),
            new Meeting(new DateTime("2060-03-10 23:12"), new Attendee("S"),
                new Description("Math consult with class 4A"), new Description("Zoom")),
            new Meeting(new DateTime("2060-10-02 15:33"), new Attendee("P"),
                new Description("Meeting with Axel Yeoh's parents"), new Description("Classroom 4D")),
        };
    }



    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Teacher sampleTeacher : getSampleTeachers()) {
            sampleAb.addTeacher(sampleTeacher);
        }
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleAb.addMeeting(sampleMeeting);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
