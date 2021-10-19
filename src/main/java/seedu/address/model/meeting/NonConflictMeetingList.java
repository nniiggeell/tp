package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.MeetingConflictException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of meetings that enforces uniqueness between its
 * datetime and does not allow nulls.
 * A meeting is considered unique by comparing using {@code Meeting#hasConflictWith(Meeting)}.
 * As such, adding of Meeting uses Meeting#hasConflictWith(Meeting)
 * for equality so as to ensure that the meeting being added does not clash with another meeting
 * in the NonConflictMeetingList. However, the removal of a meeting uses Meeting#equals(Object) so
 * as to ensure that the Meeting with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Meeting#hasConflictWith(Meeting)
 */
public class NonConflictMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains a meeting that clashes with the given argument.
     */
    public boolean hasConflictWith(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasConflictWith);
    }

    /**
     * Adds a meeting to the list and sorts the list in ascending order of datetime.
     * The Meeting must not clash with any existing meetings in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (hasConflictWith(toAdd)) {
            throw new MeetingConflictException();
        }
        internalList.add(toAdd);
        Collections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain conflicting meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsNoConflict(meetings)) {
            throw new DuplicatePersonException();
        }
        internalList.setAll(meetings);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NonConflictMeetingList // instanceof handles nulls
            && internalList.equals(((NonConflictMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetings} contains only non-conflicting meetings.
     */
    private boolean meetingsNoConflict(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).hasConflictWith(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
