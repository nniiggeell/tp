package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COPY_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_HISTORY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.logic.commands.descriptors.EditStudentDescriptor;
import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.logic.commands.student.ClearStudentCommand;
import seedu.address.logic.commands.student.CopyStudentCommand;
import seedu.address.logic.commands.student.DeleteStudentCommand;
import seedu.address.logic.commands.student.EditStudentCommand;
import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.logic.commands.student.FindStudentCommand;
import seedu.address.logic.commands.student.ListStudentCommand;
import seedu.address.logic.commands.student.MedicalHistoryCommand;
import seedu.address.logic.commands.teacher.AddTeacherCommand;
import seedu.address.logic.commands.teacher.ClearTeacherCommand;
import seedu.address.logic.commands.teacher.CopyTeacherCommand;
import seedu.address.logic.commands.teacher.DeleteTeacherCommand;
import seedu.address.logic.commands.teacher.EditTeacherCommand;
import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.logic.commands.teacher.FindTeacherCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.student.MedicalHistory;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;
import seedu.address.model.person.teacher.TeacherNameContainsKeywordsPredicate;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTeacherDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;
import seedu.address.testutil.TeacherBuilder;
import seedu.address.testutil.TeacherUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addStudent() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getStudentCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_addTeacher() throws Exception {
        Teacher teacher = new TeacherBuilder().build();
        AddTeacherCommand command = (AddTeacherCommand) parser.parseCommand(TeacherUtil.getTeacherCommand(teacher));
        assertEquals(new AddTeacherCommand(teacher), command);
    }

    @Test
    public void parseCommand_clearStudent() throws Exception {
        assertTrue(parser.parseCommand(ClearStudentCommand.COMMAND_WORD) instanceof ClearStudentCommand);
        assertTrue(parser.parseCommand(ClearStudentCommand.COMMAND_WORD + " 3") instanceof ClearStudentCommand);
    }

    @Test
    public void parseCommand_clearTeacher() throws Exception {
        assertTrue(parser.parseCommand(ClearTeacherCommand.COMMAND_WORD) instanceof ClearTeacherCommand);
        assertTrue(parser.parseCommand(ClearTeacherCommand.COMMAND_WORD + " 3") instanceof ClearTeacherCommand);
    }

    @Test
    public void parseCommand_copyStudent() throws Exception {
        CopyStudentCommand parsedCopyStudentCommand =
                (CopyStudentCommand) parser.parseCommand(CopyStudentCommand.COMMAND_WORD
                + " " + PREFIX_COPY_FIELD + "email");
        CopyStudentCommand copyStudentCommand = new CopyStudentCommand(new CopyCommandDescriptor("email"));
        assertEquals(parsedCopyStudentCommand, copyStudentCommand);
    }

    @Test
    public void parseCommand_copyTeacher() throws Exception {
        CopyTeacherCommand parsedCopyTeacherCommand =
                (CopyTeacherCommand) parser.parseCommand(CopyTeacherCommand.COMMAND_WORD
                        + " " + PREFIX_COPY_FIELD + "email");
        CopyTeacherCommand copyTeacherCommand = new CopyTeacherCommand(new CopyCommandDescriptor("email"));
        assertEquals(parsedCopyTeacherCommand, copyTeacherCommand);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_deleteTeacher() throws Exception {
        DeleteTeacherCommand command = (DeleteTeacherCommand) parser.parseCommand(
                DeleteTeacherCommand.COMMAND_WORD + " " + INDEX_FIRST_TEACHER.getOneBased());
        assertEquals(new DeleteTeacherCommand(INDEX_FIRST_TEACHER), command);
    }

    @Test
    public void parseCommand_editStudent() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_editTeacher() throws Exception {
        Teacher teacher = new TeacherBuilder().build();
        EditTeacherDescriptor descriptor = new EditTeacherDescriptorBuilder(teacher).build();
        EditTeacherCommand command = (EditTeacherCommand) parser.parseCommand(EditTeacherCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TEACHER.getOneBased() + " " + TeacherUtil.getEditTeacherDescriptorDetails(descriptor));
        assertEquals(new EditTeacherCommand(INDEX_FIRST_TEACHER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findStudent() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new StudentNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTeacher() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTeacherCommand command = (FindTeacherCommand) parser.parseCommand(
                FindTeacherCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTeacherCommand(new TeacherNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_filterStudent() throws Exception {
        // filter by involvement
        List<String> keywords = Arrays.asList("fish");
        FilterStudentCommand command = (FilterStudentCommand) parser.parseCommand(
                FilterStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterStudentCommand(new StudentInvolvementContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterMultipleTagsStudent() throws Exception {
        List<String> keywords = Arrays.asList("fishy", "carp");
        FilterStudentCommand command = (FilterStudentCommand) parser.parseCommand(
                FilterStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterStudentCommand(new StudentInvolvementContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterTeacher() throws Exception {
        // filter by involvement
        List<String> keywords = Arrays.asList("fish");
        FilterTeacherCommand command = (FilterTeacherCommand) parser.parseCommand(
                FilterTeacherCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterTeacherCommand(new TeacherInvolvementContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterMultipleTagsTeacher() throws Exception {
        List<String> keywords = Arrays.asList("fishy", "carp");
        FilterTeacherCommand command = (FilterTeacherCommand) parser.parseCommand(
                FilterTeacherCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterTeacherCommand(new TeacherInvolvementContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_medical() throws Exception {
        final MedicalHistory medicalHistory = new MedicalHistory("ADHD");
        MedicalHistoryCommand command = (MedicalHistoryCommand) parser.parseCommand(
                MedicalHistoryCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_STUDENT.getOneBased() + " " + PREFIX_MEDICAL_HISTORY + medicalHistory.value);
        assertEquals(new MedicalHistoryCommand(INDEX_FIRST_STUDENT, medicalHistory), command);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () ->
                        parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
