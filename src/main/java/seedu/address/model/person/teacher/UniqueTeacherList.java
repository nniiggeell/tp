package seedu.address.model.person.teacher;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.teacher.exceptions.DuplicateTeacherException;
import seedu.address.model.person.teacher.exceptions.TeacherNotFoundException;

/**
 * A list of teachers that enforces uniqueness between its elements and does not allow nulls.
 * A teacher is considered unique by comparing using {@code Teacher#isSameTeacher(teacher)}.
 * As such, adding and updating of teacher uses Teacher#isSameTeacher(Teacher) for
 * equality so as to ensure that the teacher being added or updated is unique in terms
 * of identity in the UniqueTeacherList. However, the removal of a teacher uses Teacher#equals(Object) so
 * as to ensure that the teacher with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Teacher#isSameTeacher(Teacher)
 */
public class UniqueTeacherList implements Iterable<Teacher> {

    private final ObservableList<Teacher> internalList = FXCollections.observableArrayList();
    private final ObservableList<Teacher> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent teacher as the given argument.
     */
    public boolean contains(Teacher toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTeacher);
    }

    /**
     * Adds a teacher to the list.
     * The teacher must not already exist in the list.
     */
    public void add(Teacher toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTeacherException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the teacher {@code target} in the list with {@code editedTeacher}.
     * {@code target} must exist in the list.
     * The teacher identity of {@code editedTeacher} must not be the same as another existing teacher in the list.
     */
    public void setTeacher(Teacher target, Teacher editedTeacher) {
        requireAllNonNull(target, editedTeacher);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TeacherNotFoundException();
        }

        if (!target.isSameTeacher(editedTeacher) && contains(editedTeacher)) {
            throw new DuplicateTeacherException();
        }

        internalList.set(index, editedTeacher);
    }

    /**
     * Removes the equivalent teacher from the list.
     * The teacher must exist in the list.
     */
    public void remove(Teacher toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TeacherNotFoundException();
        }
    }

    public void setTeachers(UniqueTeacherList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code teachers}.
     * {@code teachers} must not contain duplicate teachers.
     */
    public void setTeachers(List<Teacher> teachers) {
        requireAllNonNull(teachers);
        if (!teachersAreUnique(teachers)) {
            throw new DuplicateTeacherException();
        }
        internalList.setAll(teachers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Teacher> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Teacher> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTeacherList // instanceof handles nulls
                        && internalList.equals(((UniqueTeacherList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code teachers} contains only unique teachers.
     */
    private boolean teachersAreUnique(List<Teacher> teachers) {
        for (int i = 0; i < teachers.size() - 1; i++) {
            for (int j = i + 1; j < teachers.size(); j++) {
                if (teachers.get(i).isSameTeacher(teachers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
