package seedu.address.model.person.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class StudentInvolvementContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public StudentInvolvementContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        boolean inInvolvement = false;
        boolean inTags = false;
        boolean isTagsPresent = false;
        boolean isInvolvementPresent = true;

        for (int i = 0; i < keywords.size(); i++) {
            String keywordCurrent = keywords.get(i).toLowerCase();

            if (keywords.get(i).startsWith("t/")) {
                inTags = inTagsChecker(i, student);
                isTagsPresent = true;
                if (i == 0) {
                    isInvolvementPresent = false;
                }
                break;
            }

            if (student.getInvolvement().value.toLowerCase().contains(keywordCurrent)) {
                inInvolvement = true;
            }
        }
        if (isTagsPresent && !isInvolvementPresent) {
            return inTags;
        } else if (isTagsPresent && isInvolvementPresent) {
            return (inInvolvement && inTags);
        }

        return (inInvolvement);
    }

    private boolean inTagsChecker(int t, Student student) {
        boolean inTag = false;

        for (int i = t; i < keywords.size(); i++) {
            for (Tag s : student.getTags()) {
                String tagNameLowerCase = s.tagName.toLowerCase();
                String keywordCurrent = keywords.get(i).toLowerCase();
                if (keywordCurrent.contains(tagNameLowerCase)) {
                    inTag = true;
                }
            }
        }

        return inTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentInvolvementContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentInvolvementContainsKeywordsPredicate) other).keywords)); // state check
    }

}
