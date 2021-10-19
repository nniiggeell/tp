package seedu.address.model;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEACHER;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Collections;

import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.logic.commands.student.DeleteStudentCommand;
import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.logic.commands.teacher.AddTeacherCommand;
import seedu.address.logic.commands.teacher.DeleteTeacherCommand;
import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;


public class TypicalModels {

    //Students
    public static final StudentInvolvementContainsKeywordsPredicate FIRST_STUDENT_PREDICATE =
            new StudentInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));

    public static final ModelManager ADD_ALICE =
            new ModelManagerBuilder().with(new AddStudentCommand(ALICE)).build();

    public static final ModelManager ADD_MULTIPLE_STUDENT =
            new ModelManagerBuilder().with(new AddStudentCommand(ALICE)).with(new AddStudentCommand(BOB)).build();

    public static final ModelManager DELETING_ALICE =
            new ModelManagerBuilder()
                    .with(new AddStudentCommand(ALICE))
                    .with(new AddStudentCommand(BOB))
                    .with(new DeleteStudentCommand(INDEX_FIRST_STUDENT))
                    .build();

    public static final ModelManager FILTER_STUDENT_BY_TAG =
            new ModelManagerBuilder()
                    .with(new AddStudentCommand(ALICE))
                    .with(new AddStudentCommand(BOB))
                    .with(new DeleteStudentCommand(INDEX_FIRST_STUDENT))
                    .with(new FilterStudentCommand(FIRST_STUDENT_PREDICATE))
                    .build();

    //Teacher
    public static final TeacherInvolvementContainsKeywordsPredicate FIRST_TEACHER_PREDICATE =
            new TeacherInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));

    public static final ModelManager ADD_ALI =
            new ModelManagerBuilder().with(new AddTeacherCommand(ALI)).build();

    public static final ModelManager ADD_MULTIPLE_TEACHER =
            new ModelManagerBuilder().with(new AddTeacherCommand(ALI)).with(new AddTeacherCommand(BEN)).build();

    public static final ModelManager DELETING_ALI =
            new ModelManagerBuilder()
                    .with(new AddTeacherCommand(ALI))
                    .with(new AddTeacherCommand(BEN))
                    .with(new DeleteTeacherCommand(INDEX_FIRST_TEACHER))
                    .build();

    public static final ModelManager FILTER_TEACHER_BY_TAG =
            new ModelManagerBuilder()
                    .with(new AddTeacherCommand(ALI))
                    .with(new AddTeacherCommand(BEN))
                    .with(new DeleteTeacherCommand(INDEX_FIRST_TEACHER))
                    .with(new FilterTeacherCommand(FIRST_TEACHER_PREDICATE))
                    .build();
}
