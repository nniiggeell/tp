package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_TABLE_CHO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.BEN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.person.teacher.UniqueTeacherList;
import seedu.address.model.person.teacher.exceptions.DuplicateTeacherException;
import seedu.address.model.person.teacher.exceptions.TeacherNotFoundException;
import seedu.address.testutil.TeacherBuilder;

public class UniqueTeacherListTest {

    private final UniqueTeacherList uniqueTeacherList = new UniqueTeacherList();

    @Test
    public void contains_nullTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.contains(null));
    }

    @Test
    public void contains_teacherNotInList_returnsFalse() {
        assertFalse(uniqueTeacherList.contains(ALI));
    }

    @Test
    public void contains_teacherInList_returnsTrue() {
        uniqueTeacherList.add(ALI);
        assertTrue(uniqueTeacherList.contains(ALI));
    }

    @Test
    public void contains_teacherWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTeacherList.add(ALI);
        Teacher editedAlice = new TeacherBuilder(ALI).withTags(VALID_TAG_MONITOR)
                .build();
        assertTrue(uniqueTeacherList.contains(editedAlice));
    }

    @Test
    public void add_nullTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.add(null));
    }

    @Test
    public void add_duplicateTeacher_throwsDuplicateTeacherException() {
        uniqueTeacherList.add(ALI);
        assertThrows(DuplicateTeacherException.class, () -> uniqueTeacherList.add(ALI));
    }

    @Test
    public void setTeacher_nullTargetTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.setTeacher(null, ALI));
    }

    @Test
    public void setTeacher_nullEditedTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.setTeacher(ALI, null));
    }

    @Test
    public void setTeacher_targetTeacherNotInList_throwsTeacherNotFoundException() {
        assertThrows(TeacherNotFoundException.class, () -> uniqueTeacherList.setTeacher(ALI, ALI));
    }

    @Test
    public void setTeacher_editedTeacherIsSameTeacher_success() {
        uniqueTeacherList.add(ALI);
        uniqueTeacherList.setTeacher(ALI, ALI);
        UniqueTeacherList expectedUniqueTeacherList = new UniqueTeacherList();
        expectedUniqueTeacherList.add(ALI);
        assertEquals(expectedUniqueTeacherList, uniqueTeacherList);
    }

    @Test
    public void setTeacher_editedTeacherHasSameIdentity_success() {
        uniqueTeacherList.add(ALI);
        Teacher editedAlice =
                new TeacherBuilder(ALI).withOfficeTable(VALID_OFFICE_TABLE_CHO).withTags(VALID_TAG_MONITOR)
                .build();
        uniqueTeacherList.setTeacher(ALI, editedAlice);
        UniqueTeacherList expectedUniqueTeacherList = new UniqueTeacherList();
        expectedUniqueTeacherList.add(editedAlice);
        assertEquals(expectedUniqueTeacherList, uniqueTeacherList);
    }

    @Test
    public void setTeacher_editedTeacherHasDifferentIdentity_success() {
        uniqueTeacherList.add(ALI);
        uniqueTeacherList.setTeacher(ALI, BEN);
        UniqueTeacherList expectedUniqueTeacherList = new UniqueTeacherList();
        expectedUniqueTeacherList.add(BEN);
        assertEquals(expectedUniqueTeacherList, uniqueTeacherList);
    }

    @Test
    public void setTeacher_editedTeacherHasNonUniqueIdentity_throwsDuplicateTeacherException() {
        uniqueTeacherList.add(ALI);
        uniqueTeacherList.add(BEN);
        assertThrows(DuplicateTeacherException.class, () -> uniqueTeacherList.setTeacher(ALI, BEN));
    }

    @Test
    public void remove_nullTeacher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.remove(null));
    }

    @Test
    public void remove_teacherDoesNotExist_throwsTeacherNotFoundException() {
        assertThrows(TeacherNotFoundException.class, () -> uniqueTeacherList.remove(ALI));
    }

    @Test
    public void remove_existingTeacher_removesTeacher() {
        uniqueTeacherList.add(ALI);
        uniqueTeacherList.remove(ALI);
        UniqueTeacherList expectedUniqueTeacherList = new UniqueTeacherList();
        assertEquals(expectedUniqueTeacherList, uniqueTeacherList);
    }

    @Test
    public void setTeachers_nullUniqueTeacherList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.setTeachers((UniqueTeacherList) null));
    }

    @Test
    public void setTeachers_uniqueTeacherList_replacesOwnListWithProvidedUniqueTeacherList() {
        uniqueTeacherList.add(ALI);
        UniqueTeacherList expectedUniqueTeacherList = new UniqueTeacherList();
        expectedUniqueTeacherList.add(BEN);
        uniqueTeacherList.setTeachers(expectedUniqueTeacherList);
        assertEquals(expectedUniqueTeacherList, uniqueTeacherList);
    }

    @Test
    public void setTeachers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeacherList.setTeachers((List<Teacher>) null));
    }

    @Test
    public void setTeachers_list_replacesOwnListWithProvidedList() {
        uniqueTeacherList.add(ALI);
        List<Teacher> teacherList = Collections.singletonList(BEN);
        uniqueTeacherList.setTeachers(teacherList);
        UniqueTeacherList expectedUniqueTeacherList = new UniqueTeacherList();
        expectedUniqueTeacherList.add(BEN);
        assertEquals(expectedUniqueTeacherList, uniqueTeacherList);
    }

    @Test
    public void setTeachers_listWithDuplicateTeachers_throwsDuplicateTeacherException() {
        List<Teacher> listWithDuplicateTeachers = Arrays.asList(ALI, ALI);
        assertThrows(DuplicateTeacherException.class, () -> uniqueTeacherList.setTeachers(listWithDuplicateTeachers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTeacherList.asUnmodifiableObservableList().remove(0));
    }
}

