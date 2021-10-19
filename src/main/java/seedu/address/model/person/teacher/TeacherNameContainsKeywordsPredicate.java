package seedu.address.model.person.teacher;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class TeacherNameContainsKeywordsPredicate implements Predicate<Teacher> {
    private final List<String> keywords;

    public TeacherNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Teacher teacher) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(teacher.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeacherNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TeacherNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

