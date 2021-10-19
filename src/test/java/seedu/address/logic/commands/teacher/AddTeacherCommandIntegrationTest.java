package seedu.address.logic.commands.teacher;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.testutil.TeacherBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTeacherCommand}.
 */
public class AddTeacherCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newTeacher_success() {
        Teacher validTeacher = new TeacherBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTeacher(validTeacher);

        assertCommandSuccess(new AddTeacherCommand(validTeacher), model,
            String.format(AddTeacherCommand.MESSAGE_SUCCESS, validTeacher), expectedModel);
    }

    @Test
    public void execute_duplicateTeacher_throwsCommandException() {
        Teacher teacherInList = model.getAddressBook().getTeacherList().get(0);
        assertCommandFailure(new AddTeacherCommand(teacherInList), model, AddTeacherCommand.MESSAGE_DUPLICATE_TEACHER);
    }
}
